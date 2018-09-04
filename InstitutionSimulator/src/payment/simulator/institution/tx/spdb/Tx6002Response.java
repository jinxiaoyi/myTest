package payment.simulator.institution.tx.spdb;

import org.w3c.dom.Document;

import payment.api.tx.TxBaseResponse;


/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * wtman  2011-11-24  Create this file
 * 
 * </pre>
 * 
 */
public class Tx6002Response extends TxBaseResponse {

    public Tx6002Response(String responseMessage, String responseSignature) throws Exception {
        super(responseMessage, responseSignature);
    }

    @Override
    protected void process(Document document) throws Exception {
        // do nothing
    }

}