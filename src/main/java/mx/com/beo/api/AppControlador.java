package mx.com.beo.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
* Mexico D.F.
* Todos los derechos reservados.
*
* @author Angel Martinez Leon
*
* ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
* ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
*/

@RestController
public class AppControlador {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppControlador.class);

	/**
	 * Servicio para retornar un objeto JSON o XML o OCTET_STREAM.
     * @param Objeto JSON para recibir los datos por el RequestBody para el tipo de archivo de retorno.
     * 
     * @return Objeto JSON o XML o OCTET_STREAM.
     * @throws Si el objeto JSON o XML o OCTET_STREAM llega corrupto.
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/file", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE,
																														  MediaType.APPLICATION_XML_VALUE,
																														  MediaType.APPLICATION_OCTET_STREAM_VALUE})
	public ResponseEntity<Object> file(RequestEntity<Object> request) throws IOException {
		
		// Variables
		Map<String,Object> mapBody = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		mapBody = (Map<String,Object>) request.getBody();
		String typeFile = mapBody.get("typeFile").toString();
		final String JSON="1", XML="2", OCTEC_STREAM="3";
		ClassPathResource pdfFile = null;
		
		
		switch(typeFile){
		
			case JSON:
				LOGGER.info("EndPoint JSON in method file");
				
				map = new HashMap<String,Object>();
				map.put("type", "JSON");
				map.put("EndPoint", "JSON");
				
			    return ResponseEntity
			            .ok()
			            .contentType(
			                    MediaType.parseMediaType("application/json"))
			            .body(map);
				
			case XML:
				LOGGER.info("EndPoint XML in method file");
				
				pdfFile = new ClassPathResource("EjemploXML.xml");
				
			    return ResponseEntity
			            .ok()
			            .contentLength(pdfFile.contentLength())
			            .contentType(
			                    MediaType.parseMediaType("application/xml"))
			            .body(new InputStreamResource(pdfFile.getInputStream()));
				
			case OCTEC_STREAM:
				LOGGER.info("EndPoint PDF in method file");
				LOGGER.info("JSON: " + request.getBody().toString());
				
				pdfFile = new ClassPathResource("menu.pdf");
				
			    return ResponseEntity
			            .ok()
			            .contentLength(pdfFile.contentLength())
			            .contentType(
			                    MediaType.parseMediaType("application/octet-stream"))
			            .body(new InputStreamResource(pdfFile.getInputStream()));
				
				default:
					LOGGER.info("El tipo de archivo es incorrecto");
					
					map = new HashMap<String,Object>();
					map.put("Error", "El tipo de archivo es incorrecto");
					
				    return ResponseEntity
				            .ok()
				            .contentType(
				                    MediaType.parseMediaType("application/json"))
				            .body(map);
		
		}
		
	}
	
}