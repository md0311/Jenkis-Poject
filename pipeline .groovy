pipeline {
    agent any

    options {
        quietPeriod(2)
    }
    
    stages {
        stage('Build') {
            steps {
                echo "Using Maven to construct the code"
            }
        }
        stage('Unit and Integration Tests') {
            steps {
                echo "Using Selenium to run unit tests"
                echo "Using Selenium to perform integration tests"
            }
            post {
                success {
                    emailext to: 's223535953@deakin.edu.au',
                             subject: 'Passed the Integration and Unit Tests',
                             body: 'Integration and unit tests were successfully completed.',
                            attachLog: true
                }
                failure {
                    emailext to: 's223535953@deakin.edu.au',
                             subject: 'Failure of the Unit and Integration Tests',
                             body: 'Integration and unit tests were unsuccessful. To learn more, please review the logs.',
                             attachLog: true 
                        }
            }
        }
        stage('Code Analysis') {
            steps {
                echo "Using SonarQube for code analysis"
            }
        }

        stage('Code Improvement') {
            steps {
                echo "Improve code"
            }
        }
         
        stage('Security Scan') {
            steps {
                echo "Securing a network with OWASP Dependency-Check"
            }
            post {
                success {
                    emailext to: 's223535953@deakin.edu.au',
                             subject: 'Security Scan was successfully completed.',
                             body: 'The security scan was successfully completed. Creating a fresh commitment',
                             attachLog: true
                        }
                failure {
                    emailext to: 's223535953@deakin.edu.au',
                             subject: 'Security Check Erroneous',
                             body: 'Security Check Erroneous.',
                             attachLog: true
                        }
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo "Using an EC2 instance to deploy the application to the staging server in Amazon
"
            }
        }
        stage('Integration Tests on Staging') {
            steps {
                echo "utilising the staging environment to conduct integration tests"
            }
        }
        stage('Deploy to Production') {
            steps {
                echo "Installing the programme on the AWS EC2 instance serving as the production server"
            }
        }
    }
}
