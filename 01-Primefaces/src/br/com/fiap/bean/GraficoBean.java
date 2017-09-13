package br.com.fiap.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.fiap.bo.UsuarioBO;

@ManagedBean
public class GraficoBean {

	private PieChartModel graficoPizza;
	private LineChartModel graficoLinha;
	
	private UsuarioBO bo;
	
	@PostConstruct
	private void init(){
		bo = new UsuarioBO();
		
		graficoPizza = new PieChartModel();
		graficoPizza.set("Abaixo de média", 
				bo.contarUsuarioPorNivel(1) + bo.contarUsuarioPorNivel(2));
		graficoPizza.set("Normal", bo.contarUsuarioPorNivel(3));
		graficoPizza.set("Acima da Média", 
				bo.contarUsuarioPorNivel(5) + bo.contarUsuarioPorNivel(4));
		
		graficoPizza.setLegendPosition("e");
		graficoPizza.setShowDataLabels(true);
		
		//Valores para o gráfico de linha
		graficoLinha = new LineChartModel();
		graficoLinha.setLegendPosition("e");
		graficoLinha.getAxes().put(AxisType.X, new CategoryAxis("Mês"));
		
		LineChartSeries serie = new LineChartSeries();
		serie.setLabel("Alunos");
		
		String[] meses = {"Jan","Fev","Mar","Abr","Mai","Jun","Jul","Ago","Set","Out","Nov","Dez"};
		
		for (int i=1; i<=12; i++){
			serie.set(meses[i-1], bo.contarPorMes(i));
		}
		//Adiciona a linha no gráfico (sem isso não funciona)
		graficoLinha.addSeries(serie);
	}

	public PieChartModel getGraficoPizza() {
		return graficoPizza;
	}

	public void setGraficoPizza(PieChartModel graficoPizza) {
		this.graficoPizza = graficoPizza;
	}

	public LineChartModel getGraficoLinha() {
		return graficoLinha;
	}

	public void setGraficoLinha(LineChartModel graficoLinha) {
		this.graficoLinha = graficoLinha;
	}
	
}
