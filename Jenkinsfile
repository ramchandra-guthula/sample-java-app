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
	stage('Clone_ansible_playbook') {
	     steps {
		git branch: 'main', credentialsId: 'ram github credentials', url: 'https://github.com/ramchandra-guthula/ansible_practice.git'
		    }
		}
		
	stage('Deploy') {
	     steps {
		script {
		   dir("$WORKSPACE/ec2_tomcat/") {
		        sh '''
			  ansiblePlaybook credentialsId: 'devops_ssh_key', installation: 'ansible-2.9', playbook: '$WORKSPACE/ec2_tomcat/site.yaml'
			'''
                           }
			}
	          }	
	   }
	stage ('Clean workspace'){
	      steps {
		  cleanWs()
		    }
	        }
        }
}
