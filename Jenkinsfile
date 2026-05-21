def SERVICES = ["productcatalogue","stockmanager","shopfront"]

pipeline {
     
    agent any

    parameters {
        string(name: 'VERSION', defaultValue: '1.0.0-SNAPSHOT', description: 'Enter version')
    }

    environment {
        ARTIFACTORY_URL = 'http://3.216.159.179:8081/artifactory'
    }

    stages{
        stage('checkout') {
            agent any 
            steps {
                // Checking code from github repo
                checkout scm
            }
        }

        stage ('Set Version') {
            steps {
                script {
                    env.APP_VERSION = params.VERSION
                    if (env.BRANCH_NAME == "master") {
                        env.REPO_TYPE = "release"
                    } else {
                        env.REPO_TYPE = "snapshot"
                    }

                    echo "Branch: ${env.BRANCH_NAME}"
                    echo "Version: ${env.APP_VERSION}"
                    echo "Repo_Type: ${env.REPO_TYPE}"
                }
            }
        }

        stage ('Build') {
            steps {
                script {
                    for (svc in SERVICES) {

                        echo "Building ${svc}"
                        dir(svc) {
                            sh "mvn clean package -DskipTests -Drevision=${env.APP_VERSION}"
                        }
                    }
                }
            }
        }

        stage ('Publish Artifacts') {
            steps {
                script {

                    configFileProvider([configFile(
                        fileId: '9f47d13e-f38a-4b2a-8711-4ceb13ef0d16',
                        variable: 'MAVEN_SETTINGS'
                    )]) {
                        for (svc in SERVICES) {
                            echo "Publishing ${svc}"
                            def repo = (env.REPO_TYPE) ? "shopfront-libs-release-local" : "shopfront-libs-snapshot-local"

                            dir(svc) {
                                sh """
                                    mvn deploy \
                                    -s $MAVEN_SETTINGS \
                                    -DskipTests \
                                    -Drevision=${env.APP_VERSION}
                                """
                            }
                        }
                    }
                    
                    
                }
            }
        }
    }
    
post {
    success {
        echo "Build and Publish Successful"
    }

    failure {
        echo "Build Failed"
    }
}

}