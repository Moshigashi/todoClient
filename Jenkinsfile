pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
      }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
    }
    stages {
        stage('Static Analysis') {
            steps {
                echo 'Run the static analysis to the code' 
            }
        }
        stage('Compile') {
            steps {
                echo 'Compile the source code'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Build') {
          steps {
            sh 'docker build -t moshigashi/jenkins-docker-hub .'
          }
        }
        stage('Login') {
          steps {
            sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push') {
          steps {
            sh 'docker push moshigashi/jenkins-docker-hub'
          }
        }
        stage('Security Check') {
            steps {
                echo 'Run the security check against the application' 
            }
        }
        stage('Run Unit Tests') {
            steps {
                echo 'Run unit tests from the source code' 
            }
        }
        stage('Run Integration Tests') {
            steps {
                echo 'Run only crucial integration tests from the source code' 
            }
        }
        stage('Publish Artifacts') {
            steps {
                //input("Do you want to continue or not?")
                echo 'Save the assemblies generated from the compilation' 
            }
        }
    }
    post {
        always {
          sh 'docker logout'
        }
    }
}
