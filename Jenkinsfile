pipeline {
    agent any
    stages {
        stage ('clone'){
            steps {
          git credentialsId: 'ram github credentials', url: 'https://github.com/ramchandra-guthula/sample-java-app.git'  
          }
        }
               stage ('verfication') {
            steps {
                sh ''' 

		ls -lrt
		echo "Running on ${env.BRANCH_NAME}"

'''
            }
        }
        stage ('build'){
            tools {
  		maven 'maven 3.5.3'
		}
            steps {
                sh " mvn clean install"
            }
        }
    }
}
