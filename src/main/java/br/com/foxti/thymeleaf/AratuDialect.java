package br.com.foxti.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.foxti.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.foxti.thymeleaf.processor.MenuAttributeTagProcessor;
import br.com.foxti.thymeleaf.processor.MessageElementTagProcessor;
import br.com.foxti.thymeleaf.processor.OrderElementTagProcessor;
import br.com.foxti.thymeleaf.processor.PaginationElementTagProcessor;


@Component
public class AratuDialect extends AbstractProcessorDialect {

	public AratuDialect() {
		super("TM Automacao Aratu", "aratu", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
