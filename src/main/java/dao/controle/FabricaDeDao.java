package dao.controle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.impl.CarroDAOImpl;
import dao.impl.ClienteDAOImpl;
import dao.impl.MotoristaDAOImpl;
import dao.impl.ViagemDAOImpl;
import net.sf.cglib.proxy.Enhancer;

@Configuration
public class FabricaDeDao {

	@Bean
	public static ClienteDAOImpl getClienteDao() throws Exception {
		return getDao(dao.impl.ClienteDAOImpl.class);
	}
	
	@Bean
	public static MotoristaDAOImpl getMotoristaDao() throws Exception {
		return getDao(dao.impl.MotoristaDAOImpl.class);
	}
	@Bean
	public static CarroDAOImpl getCarroDao() throws Exception {
		return getDao(dao.impl.CarroDAOImpl.class);
	}
	@Bean
	public static ViagemDAOImpl getViagemDao() throws Exception {
		return getDao(dao.impl.ViagemDAOImpl.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDao(Class<T> classeDoDao) throws Exception {
		return (T) Enhancer.create(classeDoDao, new InterceptadorDeDAO());
	}
}