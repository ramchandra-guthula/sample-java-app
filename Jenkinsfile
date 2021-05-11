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
		ls -lrt $WORKSPACE
                '''
            }
        }
        stage ('build'){
            tools {
                maven 'maven-3.8.1'
                }
            steps {
                sh " mvn clean install"
            }
        }
	stage('Get Ansible Playbook') {
	     steps {
		git branch: 'main', credentialsId: 'ram github credentials', url: 'https://github.com/ramchandra-guthula/ansible_practice.git'
		    }
		}
		
	stage('Deploy WAR file') {
	     steps {
		script {
		   dir("$WORKSPACE/ec2_tomcat/") { 
		      	 ansiColor('xterm'){
				sh '''
				  ansiblePlaybook credentialsId: 'devops_ssh_key', installation: 'ansible-2.9', playbook: '$WORKSPACE/ec2_tomcat/site.yaml'
				  currentbuild.displayname = "my-app"
				'''
                           }
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
