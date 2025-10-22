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
        stage('Compile, test code, package in war file and store it in maven repo')
        {
        steps {
            sh 'chmod +x mvnw'
            sh 'mvn clean install'
        }
        post{
                success {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
            }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'MySonarQubeServer', credentialsId: 'sonarqubePWD') {
                    sh "mvn sonar:sonar -Dsonar.projectKey=country-service -Dsonar.projectB"
                    }
            }
        }
    }
}