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
                sh './mvnw clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh './mvnw test'
            }
        }

        stage('Package') {
            steps {
                echo ' Packaging the application...'
                sh './mvnw clean package'
            }
        }
    }
}
