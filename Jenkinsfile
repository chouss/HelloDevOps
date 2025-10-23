pipeline {
    agent any
    
    stages {
        stage('Checkout code') {
            steps {
                git branch: 'main', url: 'https://github.com/chouss/HelloDevOps.git'
            }
        }
        
        //  CI
        stage('Compile, Test, Package') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean install' 
            }
            post {
                success {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'MySonarQubeServer', credentialsId: 'sonarqubePWD') {
                    sh "./mvnw sonar:sonar -Dsonar.projectKey=country-service -Dsonar.projectB"
                }
            }
        }

                //  => Nexus:
        stage('Publish Artifact to Nexus') {
            steps {
                echo 'Publishing artifact to Nexus Repository...'
                sh './mvnw deploy' 
                archiveArtifacts artifacts: 'target/*.war, target/*.jar', onlyIfSuccessful: true
            }
        }
            // Tomcat
        stage('Deploy to Tomcat Server') {
            steps {
                echo 'Initiating deployment to remote Tomcat server...'
                
                script {
                    withCredentials([sshUserPrivateKey(credentialsId: 'tomcat-ssh-credentials', keyFileVariable: 'SSH_KEY', usernameVariable: 'SSH_USER')]) {
                        sh """
                            scp -i ${SSH_KEY} -o StrictHostKeyChecking=no target/*.war ${SSH_USER}@tomcat-host:/path/to/tomcat/webapps/app.war
                        """
                    }
                }
                echo 'Deployment complete.'
            }
        }
    }
}