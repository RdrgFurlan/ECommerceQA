pipeline {
  agent any
  stages {
    stage('Tools Version') {
      parallel {
        stage('Tools Version') {
          steps {
            sh '''java -version
                  mvn --version
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
          docker pull selenium/standalone-firefox
          docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-firefox:latest
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
  environment {
    Dev = 'DEV'
  }
}