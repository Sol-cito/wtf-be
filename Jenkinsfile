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
                sh 'cd /var/lib/jenkins/jobs/wtf-be-dev/workspace/'
                sh 'sh deploy.sh'
            }
        }
    }
}