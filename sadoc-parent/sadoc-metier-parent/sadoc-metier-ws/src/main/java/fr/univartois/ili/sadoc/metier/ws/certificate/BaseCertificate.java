/**
 * 
 */
package fr.univartois.ili.sadoc.metier.ws.certificate;

import java.security.PublicKey;
import java.util.Date;

import fr.univartois.ili.sadoc.dao.entities.CertificateType;


/**
 *
 */
public interface BaseCertificate {
	public Date getDateValidity();
	public PublicKey getPublicKey();
	public CertificateType getType();
}
