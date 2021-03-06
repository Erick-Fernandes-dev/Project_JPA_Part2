package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	private LocalDate data = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.LAZY)//PREGUIÇOSO
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	public Pedido() {
		
	}

	public Pedido(Cliente cliente) {
		
		this.cliente = cliente;
	}
	
	// vincular os valores de duas classes, ou seja, dicionar 2 valores de classes
	//valor bidirecional
	public void adicionar(ItemPedido item) {
		item.setPedido(this);
		this.getItens().add(item);
		//soma o valor total com a valor do item.
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	// getters and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
	
	
	
	//dica:
	// ManyToOne --> Quando é to one é carregado automaticamente
	// OneToMany --> Quando é to Many, não é carregado automaticamente
	// todo realacionamento to One coloque o carregamento para ser LAZY, pois o psadrão é eager
	

}
