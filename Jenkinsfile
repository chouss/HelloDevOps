pipeline {
    agent any
    tools {
        maven 'mymaven'
    }
    stages {
        stage('Checkout code')
        {
        steps {
            git branch: 'main', url: 'https://github.com/chouss/HelloDevOps.git'
        }
        }
    stage('Compile') {
            steps {
                echo 'Compiling source code...'
                withMaven(maven: 'mymaven') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                withMaven(maven: 'mymaven') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo ' Packaging the application...'
                withMaven(maven: 'mymaven') {
                    sh 'mvn clean package'
                }
            }
        }
    }
}
