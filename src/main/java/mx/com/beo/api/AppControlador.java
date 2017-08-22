package mx.com.beo.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.beo.util.HeadersParser;
import mx.com.beo.util.UtilidadesRest;

/**
* Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
* Mexico D.F.
* Todos los derechos reservados.
*
* @author NombreDelDesarrollador
*
* ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
* ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
*/

@RestController
@RequestMapping(value = "/")
public class AppControlador {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppControlador.class);

	@RequestMapping(value = "/PDF", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> PDF(RequestEntity<Object> request) throws IOException {
		
		LOGGER.info("EndPoint PDF");
		
		ClassPathResource pdfFile = new ClassPathResource("EjemploPDF.pdf");
		
	    return ResponseEntity
	            .ok()
	            .contentLength(pdfFile.contentLength())
	            .contentType(
	                    MediaType.parseMediaType("application/octet-stream"))
	            .body(new InputStreamResource(pdfFile.getInputStream()));
	}
	
	
	@RequestMapping(value = "/XML", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> XML(RequestEntity<Object> request) throws IOException {
		
		LOGGER.info("EndPoint XML");
		
		ClassPathResource pdfFile = new ClassPathResource("EjemploXML.xml");
		
	    return ResponseEntity
	            .ok()
	            .contentLength(pdfFile.contentLength())
	            .contentType(
	                    MediaType.parseMediaType("application/xml"))
	            .body(new InputStreamResource(pdfFile.getInputStream()));
	}
	
	
	@RequestMapping(value = "/JSON", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> JSON(RequestEntity<Object> request) throws IOException {
		
		LOGGER.info("EndPoint JSON");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", "JSON");
		map.put("EndPoint", "/JSON");
		
	    return ResponseEntity
	            .ok()
	            .contentType(
	                    MediaType.parseMediaType("application/json"))
	            .body(map);
	}
}
