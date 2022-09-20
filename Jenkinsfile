pipeline {
    agent any

    stages {
        stage('Pipeline Health Check') {
            steps {
                echo "Hello WTF Jenkins!!!!!"
            }
        }

        stage('Build') {
            steps {
                echo "Build start by shell script"
                sh 'cd /var/lib/jenkins/jobs/wtf-be-dev/workspace/script/'
                sh "sudo chmod +x build.sh"
                sh 'bash build.sh'
            }
        }

        stage('New Instance Health check') {
            steps {
                echo "Health check start by shell script"
                sh 'cd /var/lib/jenkins/jobs/wtf-be-dev/workspace/script/'
                sh "sudo chmod +x healthCheck.sh"
                sh 'bash healthCheck.sh'
            }
        }

        stage('Nginx Port Switching') {
            steps {
                echo "Switching by shell script"
                sh 'cd /var/lib/jenkins/jobs/wtf-be-dev/workspace/script/'
                sh "sudo chmod +x portSwitch.sh"
                sh 'bash portSwitch.sh'
            }
        }

        stage('Kill Previous Instance Process') {
            steps {
                echo "Kill Previous Instance Process by shell script"
                sh 'cd /var/lib/jenkins/jobs/wtf-be-dev/workspace/script/'
                sh "sudo chmod +x killPreviousProcess.sh"
                sh 'bash killPreviousProcess.sh'
            }
        }
    }
}