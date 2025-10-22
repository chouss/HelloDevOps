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
        stage('Compile code')
        {
        steps {
            withMaven(maven: 'mymaven') {
                sh 'mvn compile'
            }
        }
        }
        
        stage('Test code')
        {
        steps {
            withMaven(maven: 'mymaven') {
                sh 'mvn test'
            }
        }
        post{
            success {
                junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            }
        }
        }
        
        stage('Package code')
        {
            steps {
                withMaven(maven: 'mymaven') {
                    sh 'mvn package'
                }
            }
        }
    }
}