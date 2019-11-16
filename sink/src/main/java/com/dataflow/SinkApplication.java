package com.dataflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
public class SinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinkApplication.class, args);
	}

}

@EnableBinding(SampleSink.Sink.class)
class SampleSink {

	private final Log logger = LogFactory.getLog(getClass());

	// Sink application definition
	@StreamListener(Sink.SAMPLE)
	public void receive(Foo foo) {
		logger.info("******************\nAt the Sink\n******************");
		logger.info("Received transformed message " + foo.getValue() + " of type " + foo.getClass());
	}

	public interface Sink {
		String SAMPLE = "sample-sink";

		@Input(SAMPLE)
		SubscribableChannel sampleSink();
	}
}

class Foo {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}