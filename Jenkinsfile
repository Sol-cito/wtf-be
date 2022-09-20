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
                sh 'deploy.sh'
            }
        }
    }
}