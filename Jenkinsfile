pipeline {

    agent any

    environment {
         TEST_REPORT_URL = "http://localhost:8080/job/ECommerceQA/" + "${env.BUILD_NUMBER}" + "/cucumber-html-reports_fb242bb7-17b2-346f-b0a4-d7a3b25b65b4/overview-features.html"
    }

    stages {
        stage('Tools Version') {
            parallel {
                stage('Tools Version') {
                    steps {
                        sh '''
                            java -version
                            mvn --version
                            docker --version
                        '''
                    }
                }
                stage('Check for POM file in repository') {
                    steps {
                        fileExists 'pom.xml'
                    }
                }
            }
        }

        stage('Clean docker containers'){
            steps{
                sh '''
                    docker stop FirefoxStandalone -t 60 || true
                    docker container prune -f
                '''
            }
        }

        stage('Build web driver docker container') {
            steps {
                sh '''
                    docker pull selenium/standalone-firefox
                    docker run --name FirefoxStandalone -d -p 4444:4444 --shm-size="2g" selenium/standalone-firefox:latest
                '''
            }
        }

        stage('Build Solution') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh '''
                        mvn clean test || clean
                    '''
                }
            }
        }

        stage('Generate HTML report') {
            steps {
                cucumber buildStatus: 'UNSTABLE',
                    reportTitle: 'My report',
                    fileIncludePattern: '**/Cucumber.json',
                    trendsLimit: 10,
                    classifications: [
                        [
                            'key': 'Browser',
                            'value': 'Firefox'
                        ]
                    ]
                }
            }
    }
    post {
        always {
            echo 'Ending docker sessions'
            sh '''
                docker stop FirefoxStandalone -t 60
                docker container prune -f
            '''

            echo 'Begin of Teams Message Integration'
            office365ConnectorSend webhookUrl: "${env.TEAMS_WEBHOOK_URL}",
                              factDefinitions: [[name: "Test Results", template: "${env.TEST_REPORT_URL}"]]

            echo 'End of Teams Message Integration'
          }
          /*
          success {
              office365ConnectorSend webhookUrl: "${env.TEAMS_WEBHOOK_URL}",
                  factDefinitions: [[name: "Test Results", template: "${env.TEST_REPORT_URL}"]]
          }
          failure {
              office365ConnectorSend webhookUrl: "${env.TEAMS_WEBHOOK_URL}",
                  factDefinitions: [[name: "Test Results", template: "${env.TEST_REPORT_URL}"]]
          }
          */
      }
}