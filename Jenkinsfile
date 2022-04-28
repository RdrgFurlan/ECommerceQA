pipeline {
  agent any
  stages {
    stage('Tools Version') {
      parallel {
        stage('Tools Version') {
          steps {
            sh '''java -version
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

      stage('Build web driver docker image') {
        steps {
          sh '''
          docker stop FirefoxStandalone
          docker container prune -f
          docker pull selenium/standalone-firefox
          docker run --name FirefoxStandalone -d -p 4444:4444 --shm-size="2g" selenium/standalone-firefox:latest
          '''
        }
      }

    stage('Build Solution') {
      steps {
        sh '''mvn clean test
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

    stage('Send Message to Teams') {
      steps {
        echo 'Sending message to Teams'
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
              office365ConnectorSend webhookUrl: "https://qaconsultants2.webhook.office.com/webhookb2/8523f4e6-2c60-4348-aab2-c44f963b0cef@a786690b-7b5d-4f26-82c4-6de8eb5b99b3/IncomingWebhook/9161730248524ccd884964191535d559/e6c57fc7-d758-42f4-bfbf-05db838b6952",
                  factDefinitions: [[name: "Build Success", template: "Build was finalized with success"]]
          }
          failure {
              office365ConnectorSend webhookUrl: "https://qaconsultants2.webhook.office.com/webhookb2/8523f4e6-2c60-4348-aab2-c44f963b0cef@a786690b-7b5d-4f26-82c4-6de8eb5b99b3/IncomingWebhook/9161730248524ccd884964191535d559/e6c57fc7-d758-42f4-bfbf-05db838b6952",
                  factDefinitions: [[name: "Build Failure", template: "Build was finalized with errors"]]
          }
      }
  environment {
    Dev = 'DEV'
  }
}