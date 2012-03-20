<?php

class createOwnerRequest {
  public $firstName; // string
  public $lastName; // string
  public $mail; // string
}

class getOwnerRequest {
  public $mail; // string
}

class createOwnerResponse {
  public $owner; // owner
}

class signDocumentRequest {
  public $doc; // base64Binary
  public $name; // string
  public $owner; // owner
  public $competence; // competence
}

class signDocumentResponse {
  public $doc; // base64Binary
}

class createCertificateRequest {
  public $owner; // owner
}

class createCertificateResponse {
}

class verifyDocumentRequest {
  public $doc; // base64Binary
  public $document; // document
  public $owner; // owner
}

class verifyDocumentResponse {
  public $validation; // boolean
}

class getDocumentInformationsRequest {
  public $idDocument; // integer
}

class getDocumentInformationsResponse {
  public $owner; // owner
  public $competence; // competence
}

class importDocumentRequest {
  public $owner; // owner
}

class importDocumentResponse {
  public $document; // document
}

class importCompetencesRequest {
  public $document; // document
}

class importCompetencesResponse {
  public $competence; // competence
}

class getDocumentRequest {
  public $idDocument; // integer
}

class getDocumentResponse {
  public $document; // document
}

class owner {
  public $id; // integer
  public $firstName; // string
  public $lastName; // string
  public $mail; // string
}

class competence {
  public $id; // integer
  public $name; // string
  public $description; // string
  public $acronym; // string
  public $creationDate; // date
}

class document {
  public $id; // integer
  public $name; // string
  public $checkSum; // string
  public $pk7; // base64Binary
}


/**
 * SadocServicesService class
 * 
 *  
 * 
 * @author    {author}
 * @copyright {copyright}
 * @package   {package}
 */
class SadocServicesService extends SoapClient {

  private static $classmap = array(
                                    'createOwnerRequest' => 'createOwnerRequest',
                                    'getOwnerRequest' => 'getOwnerRequest',
                                    'createOwnerResponse' => 'createOwnerResponse',
                                    'signDocumentRequest' => 'signDocumentRequest',
                                    'signDocumentResponse' => 'signDocumentResponse',
                                    'createCertificateRequest' => 'createCertificateRequest',
                                    'createCertificateResponse' => 'createCertificateResponse',
                                    'verifyDocumentRequest' => 'verifyDocumentRequest',
                                    'verifyDocumentResponse' => 'verifyDocumentResponse',
                                    'getDocumentInformationsRequest' => 'getDocumentInformationsRequest',
                                    'getDocumentInformationsResponse' => 'getDocumentInformationsResponse',
                                    'importDocumentRequest' => 'importDocumentRequest',
                                    'importDocumentResponse' => 'importDocumentResponse',
                                    'importCompetencesRequest' => 'importCompetencesRequest',
                                    'importCompetencesResponse' => 'importCompetencesResponse',
                                    'getDocumentRequest' => 'getDocumentRequest',
                                    'getDocumentResponse' => 'getDocumentResponse',
                                    'owner' => 'owner',
                                    'competence' => 'competence',
                                    'document' => 'document',
                                   );

  public function SadocServicesService($wsdl = "sadoc.wsdl", $options = array()) {
    foreach(self::$classmap as $key => $value) {
      if(!isset($options['classmap'][$key])) {
        $options['classmap'][$key] = $value;
      }
    }
    parent::__construct($wsdl, $options);
  }

  /**
   *  
   *
   * @param createOwnerRequest $createOwnerRequest
   * @return createOwnerResponse
   */
  public function createOwner(createOwnerRequest $createOwnerRequest) {
    return $this->__soapCall('createOwner', array($createOwnerRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param signDocumentRequest $signDocumentRequest
   * @return signDocumentResponse
   */
  public function signDocument(signDocumentRequest $signDocumentRequest) {
    return $this->__soapCall('signDocument', array($signDocumentRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param getOwnerRequest $getOwnerRequest
   * @return void
   */
  public function getOwner(getOwnerRequest $getOwnerRequest) {
    return $this->__soapCall('getOwner', array($getOwnerRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param getDocumentInformationsRequest $getDocumentInformationsRequest
   * @return getDocumentInformationsResponse
   */
  public function getDocumentInformations(getDocumentInformationsRequest $getDocumentInformationsRequest) {
    return $this->__soapCall('getDocumentInformations', array($getDocumentInformationsRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param importDocumentRequest $importDocumentRequest
   * @return importDocumentResponse
   */
  public function importDocument(importDocumentRequest $importDocumentRequest) {
    return $this->__soapCall('importDocument', array($importDocumentRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param verifyDocumentRequest $verifyDocumentRequest
   * @return verifyDocumentResponse
   */
  public function verifyDocument(verifyDocumentRequest $verifyDocumentRequest) {
    return $this->__soapCall('verifyDocument', array($verifyDocumentRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param getDocumentRequest $getDocumentRequest
   * @return getDocumentResponse
   */
  public function getDocument(getDocumentRequest $getDocumentRequest) {
    return $this->__soapCall('getDocument', array($getDocumentRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param importCompetencesRequest $importCompetencesRequest
   * @return importCompetencesResponse
   */
  public function importCompetences(importCompetencesRequest $importCompetencesRequest) {
    return $this->__soapCall('importCompetences', array($importCompetencesRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param createCertificateRequest $createCertificateRequest
   * @return createCertificateResponse
   */
  public function createCertificate(createCertificateRequest $createCertificateRequest) {
    return $this->__soapCall('createCertificate', array($createCertificateRequest),       array(
            'uri' => 'http://sadoc.com/ac/schemas',
            'soapaction' => ''
           )
      );
  }

}

?>
