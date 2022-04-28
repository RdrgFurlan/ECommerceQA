pipeline {
    agent any
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
                    docker stop FirefoxStandalone || true
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
                sh '''
                    mvn clean test
                '''
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
            echo 'One way or another, I have finished'
            sh '''
                docker stop FirefoxStandalone
                docker container prune -f
            '''
            deleteDir() /* clean up our workspace */
          }
          success {
              office365ConnectorSend webhookUrl: ${env.TEAMS_WEBHOOK_URL},
                  factDefinitions: [[name: "Test Results", template: http://localhost:8080/job/ECommerceQA/${env.BUILD_NUMBER}/cucumber-html-reports_fb242bb7-17b2-346f-b0a4-d7a3b25b65b4/overview-features.html]]
          }
          failure {
              office365ConnectorSend webhookUrl: ${env.TEAMS_WEBHOOK_URL},
                  factDefinitions: [[name: "Test Results", template: http://localhost:8080/job/ECommerceQA/${env.BUILD_NUMBER}/cucumber-html-reports_fb242bb7-17b2-346f-b0a4-d7a3b25b65b4/overview-features.html]]
          }
      }
  environment {
    Dev = 'DEV'
  }
}