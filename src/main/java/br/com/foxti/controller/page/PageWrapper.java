package br.com.foxti.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {

	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	public static final int NUMERO_PAGINAS_EXIBIDAS = 10;
	private int inicio, fim;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		String httpUrl = httpServletRequest.getRequestURL()
				.append(httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20").replaceAll("excluido", "");
		this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
		if (page.getTotalPages() < NUMERO_PAGINAS_EXIBIDAS) {
			this.inicio = 1;
			this.fim = page.getTotalPages();
		} else {
			if (page.getNumber() + 1 <= NUMERO_PAGINAS_EXIBIDAS - NUMERO_PAGINAS_EXIBIDAS / 2) {
				this.inicio = 1;
				this.fim = NUMERO_PAGINAS_EXIBIDAS;
			} else if (page.getNumber() <= page.getTotalPages() - 1 - NUMERO_PAGINAS_EXIBIDAS / 2) {
				this.inicio = page.getNumber() + 1 - NUMERO_PAGINAS_EXIBIDAS / 2;
				this.fim = page.getNumber() + 1 + NUMERO_PAGINAS_EXIBIDAS / 2;
			} else {
				this.inicio = page.getTotalPages() + 1 - NUMERO_PAGINAS_EXIBIDAS;
				this.fim = page.getTotalPages();
			}

		}
	}

	public List<T> getConteudo() {
		return page.getContent();
	}

	public boolean isVazia() {
		return page.getContent().isEmpty();
	}

	public int getAtual() {
		return page.getNumber();
	}

	public boolean isPrimeira() {
		return page.isFirst();
	}

	public boolean isUltima() {
		return page.isLast();
	}

	public int getInicio() {
		return inicio;
	}

	public int getFim() {
		return fim;
	}

	public int getTotal() {
		return page.getTotalPages();
	}

	public String irParaPagina(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}

	public String urlOrdenada(String propriedade) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());

		String valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));

		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}

	public String inverterDirecao(String propriedade) {
		String direcao = "asc";

		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if (order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}

		return direcao;
	}

	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}

	public boolean ordenada(String propriedade) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;

		if (order == null) {
			return false;
		}

		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}

}
