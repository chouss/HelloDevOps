1 pipeline {
2   agent any
3   tools {
4       maven 'mymaven'
5   }
6   stages {
7         stage('Checkout code')
8         {
9         steps {
10             git branch: 'main', url: 'https://github.com/chouss/HelloDevOps.git'
11         }
12         }
13     stage('Compile, test code, package in war file and store it in maven repo')
14     {
15     steps {
            sh 'chmod +x mvnw'
16         sh 'mvn clean install'
17     }
18     post{
19         success {
20             junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
21             }
22             }
23     }
    24 stage('SonarQube Analysis') {
    25 
    26     steps {
    27 
    28         withSonarQubeEnv(installationName: 'MySonarQubeServer', credentialsId: 'sonarqubePWD') {
    29 
    30             sh "mvn sonar:sonar -Dsonar.projectKey=country-service -Dsonar.projectB"
    31 
    32         }
    33 
    34     }
    35 }
    }
}