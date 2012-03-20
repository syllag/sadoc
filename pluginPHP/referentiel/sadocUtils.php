<?php 
error_reporting(E_ALL);
require("SadocServicesService.php");

function parseEnv($xmlstr) {  
	$xml = simplexml_load_string($xmlstr);
   $xmlchilds=$xml->children('SOAP-ENV',true);
   $obj=$xmlchilds[1]->children();
   		
	return (array) $obj[0]; 
}

function getOrCreateOwner($ws,$firstName,$lastName,$mail) {
	$ow=new createOwnerRequest();
	
	$ow->firstName=$firstName;
	$ow->lastName=$lastName;
	$ow->mail=$mail;
	
	$ws->createOwner($ow);

	$res=parseEnv($ws->__getLastResponse());
	
	return $res;
}

function signDocument($ws,$doc,$filename,$owner,$competence) { // renvoie une chaine base64
	$sdr = new signDocumentRequest();
	
	$sdr->doc=$doc;
	$sdr->name=$filename;
	$sdr->owner=$owner;
	$sdr->competence=$competence;

	$docSigned = $ws->signDocument($sdr);

	$res = $docSigned;
	
	return $res;
}


function printSoapEnv($sc) {
	 echo "\n****** Last Request ******\n";
    echo $sc->__getLastRequest (),"\n";
    
    echo "\n****** Last Response ******\n";
    echo $sc->__getLastResponse (),"\n";

}

/*
// EXEMPLE (à virer) /

$ws = new SadocServicesService("http://192.168.64.108:8080/sadocWeb/services/sadoc.wsdl",array('trace' => 1));
//$ws = new SadocServicesService("http://192.168.64.214:8080/sadocWeb/services/sadoc.wsdl",array('trace' => 1));

try {	
	
	// Création du owner 
	echo "Création d'un owner\n";
	$ow = getOrCreateOwner($ws,"Fabien","Delorme","delorme@cril.fr");
	
	// Signature du fichier / 
	$file="Contacts.pdf";
	echo "Traitement de fichier: $file\n";
	
	$fstr=file_get_contents($file);
	echo 'taille $fst: ',strlen($fstr),"\n";
	$doc = base64_encode ($fstr);
	
	$comp = new competence();
	$comp->id=12;
	$comp->name="D1.1";
 	$comp->description="D1.1";
 	//$comp->creationDate="2002-09-24";
	$comp->acronym="C2I:2012-02-22:D1:1";

	echo "Sign document - ";
	$file=signDocument($ws,$doc,$file,$ow,$comp);
	echo " OK\n\n";

	$fp = fopen('toto13.pdf', 'w');
	fwrite($fp,base64_decode($file->doc));
	
} catch (Exception $e) {
	echo 'Exception ',$e->getCode(),' reçue : ',  $e->getMessage(), "\n"; 
	printSoapEnv($ws);
	
}
*/
?>
