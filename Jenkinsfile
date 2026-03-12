pipeline {
     
    agent none

    stages{
        stage('checkout') {
            agent any 
            steps {
                // Checking code from github repo
                checkout scm
            }
        }
    }

}