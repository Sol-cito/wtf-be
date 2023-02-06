def ADDRESS
def PORT
def BLUE_PORT
def GREEN_PORT
def IDLE_SERVICE_NAME
def IDLE_PORT
def CURRENT_SERVICE_NAME
def CURRENT_PORT

pipeline {
    agent any

    stages {
        stage('Check parameterized Profile') {
            steps {
                script {
                    echo "Current profile is ${PROFILE}"
                }
            }
        }

        stage('Pipeline Health Check') {
            steps {
                echo "Hello WTF Jenkins!!!!!"
            }
        }

        stage('Temp'){
            steps {
                echo "temp"
                script {
                    source ./initProperties.sh
                }
                echo "temp result : ${IDLE_SERVICE_NAME}"
            }
        }

        stage('Set global variables') {
            steps {
                echo "Set global variables by initProperties script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash initProperties.sh'
                }
            }
        }

        stage('Build') {
            steps {
                echo "Build start by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash build.sh'
                }
            }
        }
        stage('New Instance Health check') {
            steps {
                echo "Health check start by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash healthCheck.sh'
                }
            }
        }
        stage('Nginx Port Switching') {
            steps {
                echo "Switching by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash portSwitch.sh'
                }
            }
        }
        stage('Kill Previous Instance Process') {
            steps {
                echo "Kill Previous Instance Process by shell script"
                dir("/var/lib/jenkins/jobs/wtf-be-${PROFILE}/workspace/deploy-script") {
                    sh 'bash killPreviousProcess.sh'
                }
            }
        }
    }
}