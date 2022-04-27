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

    stage('Build Solution') {
      steps {
        sh '''mvn clean test
             '''
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