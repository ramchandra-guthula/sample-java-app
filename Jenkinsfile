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
		        sh '''
		cp $WORKSPACE/ec2_tomcat/ansible.cfg .
		chmod 400 $WORKSPACE/ec2_tomcat/devops.pem
		ansible-playbook --private-key $WORKSPACE/ec2_tomcat/devops.pem $WORKSPACE/ec2_tomcat/site.yaml
		'''
		    }
		
		}
		stage ('Clean workspace'){
		    steps {
		    cleanWs()
		    }
		}
          }
}
