pipeline {
    agent any

    stages {
        stage('Dummy Stage') {
            steps {
                echo 'This is a dummy stage running in Jenkins!'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
