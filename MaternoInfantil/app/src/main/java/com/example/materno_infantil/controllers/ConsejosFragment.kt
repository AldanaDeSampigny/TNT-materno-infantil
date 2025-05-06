package com.example.materno_infantil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.models.Consejo
import com.example.materno_infantil.adapters.ConsejoAdapter

class ConsejosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    //private lateinit var binding: FragmentConsejosLactanciaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_consejos, container, false)
        recyclerView = rootView.findViewById(R.id.consejosLactanciaRecyclerView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nombreCategoria = "enfermedades respiratorias"

        val adapter = ConsejoAdapter(obtenerListaDeConsejos(nombreCategoria))
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    fun obtenerListaDeConsejos(nombreCategoria: String): List<Consejo> {
        val listaConsejosLactancia = listOf(
            Consejo("Inicio temprano", "Amamanta a tu bebé tan pronto como sea posible después del nacimiento.", R.drawable.lactancia1),
            Consejo("Buena prendida", "Asegúrate de que el bebé esté bien prendido al pecho para evitar dolor y asegurar una buena alimentación.", R.drawable.lactancia2),
            Consejo("Lactancia a demanda", "Amamanta a demanda, es decir, cuando el bebé muestre signos de hambre.", R.drawable.lactancia3),
            Consejo("Ambos pechos", "Ofrece ambos pechos en cada toma.", R.drawable.lactancia4),
            Consejo("Ambiente tranquilo", "Busca un lugar tranquilo y cómodo para amamantar.", R.drawable.lactancia5),
            Consejo("Hidratación y nutrición", "Mantén una buena hidratación y nutrición.", R.drawable.lactancia6),
            Consejo("No compararse", "No te compares con otras mamás, cada experiencia de lactancia es única.", R.drawable.lactancia7),
            Consejo("Buscar apoyo", "Si tienes dudas o problemas, busca apoyo de profesionales de la salud o grupos de lactancia.", R.drawable.lactancia8)
        )

        val listaConsejosSueñoSeguro = listOf(
            Consejo("Dormir de lado", "Desde el segundo trimestre, duerme sobre el lado izquierdo para mejorar la circulación sanguínea hacia el bebé.", R.drawable.dormir1),
            Consejo("Evita colchones blandos", "Asegúrate de dormir en un colchón firme que mantenga una postura adecuada durante el embarazo.", R.drawable.dormir2),
            Consejo("Ambiente seguro", "Prepara un espacio libre de almohadas, juguetes u objetos sueltos donde el bebé dormirá.", R.drawable.dormir3),
            Consejo("Evita el sobrecalentamiento", "No sobreabrigues al bebé ni mantengas la habitación demasiado caliente.", R.drawable.dormir4),
            Consejo("No fumes", "Evita fumar o exponerte al humo del cigarrillo durante el embarazo para reducir el riesgo de muerte súbita del lactante.", R.drawable.dormir5)
        )

        val listaConsejosAlimentacion = listOf(
            Consejo("Alimentación equilibrada", "Consume una dieta variada que incluya frutas, verduras, cereales integrales, proteínas magras y lácteos.", R.drawable.alimantacion1),
            Consejo("Ácido fólico diario", "Toma suplementos de ácido fólico antes y durante el embarazo para prevenir defectos del tubo neural.", R.drawable.alimentacion2),
            Consejo("Hierro y calcio", "Aumenta la ingesta de hierro y calcio para apoyar el desarrollo del bebé y evitar deficiencias.", R.drawable.alimentacion3),
            Consejo("Hidratación constante", "Bebe al menos 8 vasos de agua al día para mantenerte bien hidratada.", R.drawable.lactancia6),
            Consejo("Evita alimentos crudos", "No consumas carnes, huevos o pescados crudos para prevenir infecciones como la toxoplasmosis o la listeriosis.", R.drawable.alimentacion5),
            Consejo("Limita la cafeína", "Reduce el consumo de cafeína a menos de 200 mg diarios (aproximadamente una taza de café).", R.drawable.alimentacion6),
            Consejo("Nada de alcohol", "Evita completamente el alcohol durante el embarazo, ya que puede afectar el desarrollo del bebé.", R.drawable.alimentacion7),
            Consejo("Controla el aumento de peso", "Sigue las recomendaciones médicas sobre el aumento de peso adecuado según tu situación.", R.drawable.alimentacion8),
        )

        val listaConsejosEnfermedadesRespiratorias = listOf(
            Consejo("Vacunación al día", "Aplicate la vacuna contra la gripe y la vacuna triple bacteriana acelular (dTpa) durante el embarazo para protegerte y proteger al bebé.", R.drawable.respiratorias1),
            Consejo("Evita el contacto con enfermos", "Reducí el riesgo de contagio evitando el contacto cercano con personas que tengan síntomas respiratorios.", R.drawable.respiratorias2),
            Consejo("Lavado de manos frecuente", "Lavate las manos regularmente con agua y jabón, especialmente después de estar en espacios públicos.", R.drawable.respiratorias3),
            Consejo("Ambientes ventilados", "Mantené los espacios bien ventilados para reducir la concentración de virus y bacterias en el aire.", R.drawable.respiratorias4),
        )

        if (nombreCategoria == "lactancia")
            return listaConsejosLactancia;

        if(nombreCategoria =="sueño seguro")
            return listaConsejosSueñoSeguro;

        if (nombreCategoria == "alimentacion")
            return listaConsejosAlimentacion;

        if (nombreCategoria =="enfermedades respiratorias")
            return listaConsejosEnfermedadesRespiratorias

        return listOf();
    }
}