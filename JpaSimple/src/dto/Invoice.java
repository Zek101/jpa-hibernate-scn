package dto;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Invoice {
	
	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	//@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST) @JoinColumn(name="INVOICE_ID")
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST) @JoinColumn(name="INVOICE_ID")
    Set<InvoiceLine> invoiceLines = new HashSet<InvoiceLine>();

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}
	
	public void addInvoices(InvoiceLine...invLines){
		for(InvoiceLine inv: invLines){
			//invoiceLines.add(inv);
			invoiceLines.add(inv);
		}
	}
	
}
