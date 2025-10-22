pipeline {
    agent any
    tools {
        jdk 'JKD21'
    }
    stages {
        // update
        stage('Display a message'){
            steps{
                echo 'hello from github!'
            }
        }
        stage('Checkout code') {
            steps {
                git branch: 'main', url: 'https://github.com/chouss/HelloDevOps.git', credentialsId: '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'
            }
        }
        stage('Compile code') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package'
            }
        }
    }

}
