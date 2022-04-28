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
      }
  environment {
    Dev = 'DEV'
  }
}