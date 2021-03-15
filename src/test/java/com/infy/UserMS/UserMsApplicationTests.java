package com.infy.UserMS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;




import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.infy.UserMS.dto.LoginDTO;
import com.infy.UserMS.entity.BuyerEntity;
import com.infy.UserMS.entity.SellerEntity;
import com.infy.UserMS.repository.BuyerRepository;
import com.infy.UserMS.repository.CartRepository;
import com.infy.UserMS.repository.SellerRepository;
import com.infy.UserMS.service.BuyerService;
import com.infy.UserMS.service.SellerService;
import com.infy.validator.Validator;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserMsApplicationTests {
    @Mock
    BuyerRepository buyerRepo;
    
    @Mock
    SellerRepository sellerRepo;
    
    @Mock
    CartRepository cartRepo;
    
    @Autowired 
    BuyerService buyerService;
    
    @Autowired
    SellerService sellerService;
    
    @InjectMocks
    BuyerService buyerServiceMock = new BuyerService();
    
	@Test
	public void checkBuyerByEmailValid() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("bhai@gmail.com");
		loginDTO.setPassword("Bharti$456");
		assertTrue(buyerService.login(loginDTO));
		
	}
	@Test
	public void checkBuyerByEmailInValid() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("bharti@gmail.com");
		loginDTO.setPassword("Bharti456");
		assertFalse(buyerService.login(loginDTO));
		
	}
	@Test
	public void checkRegistrationBuyer() throws Exception{
		BuyerEntity buyer = new BuyerEntity();
		buyer.setBuyerId(34);
		buyer.setEmail("tammyman@gmail.com");
		buyer.setIsActive(1);
		buyer.setIsPrivileged(0);
		buyer.setName("yamnat");
		buyer.setPassword("Tammy$234");
		buyer.setPhonenumber("9876543210");
		buyer.setRewardPoints(1000);
		Mockito.when(buyerRepo.save(buyer)).thenReturn(buyer);
		assertEquals(buyer,buyerRepo.save(buyer));
	}
	@Test
	public void isPrivileged() throws Exception{
		assertFalse(buyerService.privilegedBuyer(1));
	}
	
	@Test
	public void getModifiedAmount() throws Exception{
		int amount = 2000;
		int buyerId = 1;
		double amt = buyerService.getModifiedAmount(amount, buyerId);
		assertEquals(1995 ,(int) amt);
	}
	@Test
	public void checkSellerByEmailValid() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("tanmay@gmail.com");
		loginDTO.setPassword("Tanmay@123");
		assertTrue(sellerService.login(loginDTO));
		
	}
	@Test
	public void checkRegistrationSeller() throws Exception{
		SellerEntity seller = new SellerEntity();
		seller.setSellerId(35);
		seller.setEmail("tammyman@gmail.com");
		seller.setName("yamnat");
		seller.setPassword("Tammy$234");
		seller.setPhonenumber("9876543210");
		seller.setIsActive(0);
		Mockito.when(sellerRepo.save(seller)).thenReturn(seller);
		assertEquals(seller,sellerRepo.save(seller));
	}
	@Test
	public void validateName() throws Exception{
		assertTrue(Validator.validateName("Tanmay"));
	}
	@Test
	public void validateEmail() throws Exception{
		assertTrue(Validator.validateEmailId("tanmay@gmail.com"));
	}
	@Test
	public void validatePhoneNumber() throws Exception{
		assertFalse(Validator.validatePhonenumber("98765432100"));
	}
	@Test
	public void validateEmailId() throws Exception{
		assertTrue(Validator.validatePassword("Tanmay#2345"));
	}
	

	
	
	
	
	
	
	

}
