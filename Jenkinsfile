node {
 
  stage('Checkout') {
	  git url: 'https://github.com/eric-r-ramos/dexter.git'
	  
	  def mvnHome = tool 'maven-3'
	  env.PATH = "${mvnHome}/bin:${env.PATH}"
  }
  
  stage('Build'){
	sh 'mvn clean package'
  }

}