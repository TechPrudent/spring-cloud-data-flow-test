package com.dataflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
public class ProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}

}

@EnableBinding(Processor.class)
class SampleTransformer {

	private static final String TRANSFORMATION_VALUE = "HI";

	private final Log logger = LogFactory.getLog(getClass());

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Bar receive(Bar bar) {
		logger.info("******************\nAt the transformer\n******************");
		logger.info("Received value " + bar.getValue() + " of type " + bar.getClass());
		logger.info("Transforming the value to " + TRANSFORMATION_VALUE + " and with the type " + bar.getClass());
		bar.setValue(TRANSFORMATION_VALUE);
		return bar;
	}
}

class Bar {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}