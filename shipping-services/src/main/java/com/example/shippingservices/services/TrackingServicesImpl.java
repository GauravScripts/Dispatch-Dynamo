package com.example.shippingservices.services;

import com.example.shippingservices.domain.BookingDetails;
import com.example.shippingservices.domain.CalculatedPrice;
import com.example.shippingservices.domain.CityData;
import com.example.shippingservices.domain.VendorModel;
import com.example.shippingservices.repository.CityDataRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrackingServicesImpl implements TrackingServices{

    @Autowired
    private CityDataRepository cityDataRepository;

    @Override
    public Double calculateDistance(String city1 , String city2) {
        System.out.println("City1"+city1+"      "+"City2"+city2);
        System.out.println("************************************************************");
        Optional<CityData> c1 = cityDataRepository.findById(city1);
        Optional<CityData> c2 = cityDataRepository.findById(city2);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(distance(c1.get().getLat(),c2.get().getLat(),c1.get().getLon(),c2.get().getLon()));
        Double dis=distance(c1.get().getLat(),c2.get().getLat(),c1.get().getLon(),c2.get().getLon());
        return dis;
    }
    @Override
    public List<CityData> addData(List<CityData> city) {

        return cityDataRepository.saveAll(city);
    }

    public static double distance(double lat1,double lat2, double lon1,double lon2)
    {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result

        double d = c*r;
        d = d+d*0.17;
        return d;
    }

public List<VendorModel> getVendors() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().GET().header("name","value").uri(URI.create("http://localhost:8083/api/vendor/getall")).build();
    HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
    String a = response.body().substring(11,response.body().length()-301);
    System.out.println(a);
    ObjectMapper mapper = new ObjectMapper();
    List<VendorModel> list = mapper.readValue(a, new TypeReference<List<VendorModel>>() {
    });
    System.out.println();
    System.out.println(list);
    System.out.println(list.size());

    for(int i =0;i< list.size();i++)
    {
        List<VendorModel> list1 = new ArrayList<>();
       VendorModel v1 = new VendorModel(list.get(i).getEmailId(),list.get(i).getName(),list.get(i).getAddress(),list.get(i).getCompanyName(),list.get(i).getOfficeContact(),list.get(i).getOfficeAddress(),list.get(i).getCities(),list.get(i).getPrice());
        list1.add(v1);
    }
    return list;
}

public List<VendorModel> filterVendorsbyCity(String city1 ,List<VendorModel> list1)
{
    System.out.println("************************************************");
    System.out.println(city1);
    System.out.println();
    System.out.println("All list of vendors");
    System.out.println(list1);
    List<VendorModel> list3 = new ArrayList<>();
    System.out.println("***********************");
    for(int i=0;i< list1.size();i++)
    {
        if(list1.get(i).getCities()==null)
        {
            System.out.println("null city ");
        }
        else
        if(list1.get(i).getCities().contains(city1))
        {
            System.out.println("**********************************************");
            list3.add(list1.get(i));
            System.out.println(list3);
            System.out.println("***********************************************");
        }
    }
    return list3;
}


public List<VendorModel> filteredCities(String city1 , String city2 ) throws IOException, InterruptedException {
    List<VendorModel> list1 = getVendors();
    return filterVendorsbyCity(city1 ,list1);
}

public List<CalculatedPrice> getPrice(BookingDetails bookingDetails) throws IOException, InterruptedException {
    List<CalculatedPrice> list1 = new ArrayList<>();
    if(bookingDetails.getShipmentType().equalsIgnoreCase("International"))
    {
        list1 = getInternaTionalPriceByCity(bookingDetails.getFrom().getCity(),bookingDetails.getTo().getCity(),bookingDetails.getModeOfTransport());
    }
    else
        if(bookingDetails.getShipmentType().equalsIgnoreCase("Domestic"))
        {
            list1 = getDomesticPriceByCity(bookingDetails.getFrom().getCity(),bookingDetails.getTo().getCity(),bookingDetails.getModeOfTransport());
        }
        return list1 ;
}



    public List<CalculatedPrice> getDomesticPriceByCity(String city1 , String city2 , String mode) throws IOException, InterruptedException {
    Double a = calculateDistance(city1,city2);
    List<CalculatedPrice> list1 = new ArrayList<>();
    List<VendorModel> list2 = filterVendorsbyCity(city1,getVendors());
    System.out.println(list2);
    for(int i =0 ;i<list2.size();i++) {
    if(mode.equalsIgnoreCase("Road"))
    {
            System.out.println("hello Road");
            CalculatedPrice c1 = new CalculatedPrice();
            c1.setVendorId(list2.get(i).getEmailId());
            c1.setVendorName(list2.get(i).getCompanyName());
            c1.setVendorContact(list2.get(i).getOfficeContact());
            c1.setModeOfTransport("Road");
            c1.setPrice(Math.floor((list2.get(i).getPrice().getDomesticPrice().getByRoad()*a)/10));
            list1.add(c1);

        System.out.println(list1);

    }
    else
    if(mode.equalsIgnoreCase("railways"))
    {
            System.out.println("Hello Railway");
            CalculatedPrice c1 = new CalculatedPrice();
        c1.setVendorId(list2.get(i).getEmailId());
            c1.setVendorName(list2.get(i).getCompanyName());
            c1.setVendorContact(list2.get(i).getOfficeContact());
            c1.setModeOfTransport("Railway");
            c1.setPrice(Math.floor((list2.get(i).getPrice().getDomesticPrice().getByRailway()*a)/10));
            list1.add(c1);
        System.out.println(list1);
    }
    else
        if(mode.equalsIgnoreCase("Air"))
        {
            System.out.println("hello Air");

                CalculatedPrice c1 = new CalculatedPrice();
            c1.setVendorId(list2.get(i).getEmailId());
                c1.setVendorName(list2.get(i).getCompanyName());
                c1.setVendorContact(list2.get(i).getOfficeContact());
                c1.setModeOfTransport("Air");
                c1.setPrice(Math.floor((list2.get(i).getPrice().getDomesticPrice().getByAir()*a)/10));
                list1.add(c1);
            }
            System.out.println(list1);
        }

    return list1;
}

@Override
    public List<CalculatedPrice> getInternaTionalPriceByCity(String city1 , String city2 , String mode) throws IOException, InterruptedException {
        Double a = calculateDistance(city1,city2);
        List<CalculatedPrice> list1 = new ArrayList<>();
        List<VendorModel> list2 = filterVendorsbyCity(city1,getVendors());
        System.out.println(list2);
        for(int i =0 ;i<list2.size();i++) {
            if(mode.equalsIgnoreCase("Road"))
            {
                System.out.println("hello Road");
                CalculatedPrice c1 = new CalculatedPrice();
                c1.setVendorId(list2.get(i).getEmailId());
                c1.setVendorName(list2.get(i).getCompanyName());
                c1.setVendorContact(list2.get(i).getOfficeContact());
                c1.setModeOfTransport("Road");
                c1.setPrice(Math.floor((list2.get(i).getPrice().getInternationalPrice().getByRoad()*a)/80));
                list1.add(c1);

                System.out.println(list1);

            }
            else
            if(mode.equalsIgnoreCase("railways"))
            {
                System.out.println("Hello Railway");
                CalculatedPrice c1 = new CalculatedPrice();
                c1.setVendorId(list2.get(i).getEmailId());
                c1.setVendorName(list2.get(i).getCompanyName());
                c1.setVendorContact(list2.get(i).getOfficeContact());
                c1.setModeOfTransport("Railway");
                c1.setPrice(Math.floor((list2.get(i).getPrice().getInternationalPrice().getByRailway()*a)/80));
                list1.add(c1);
                System.out.println(list1);
            }
            else
            if(mode.equalsIgnoreCase("Air"))
            {
                System.out.println("hello Air");

                CalculatedPrice c1 = new CalculatedPrice();
                c1.setVendorId(list2.get(i).getEmailId());
                c1.setVendorName(list2.get(i).getCompanyName());
                c1.setVendorContact(list2.get(i).getOfficeContact());
                c1.setModeOfTransport("Air");
                c1.setPrice(Math.floor((list2.get(i).getPrice().getDomesticPrice().getByAir()*a)/80));
                list1.add(c1);
            }
            System.out.println(list1);
        }

        return list1;
    }




@Override
public Optional<CityData> returnCityData(String cityname)
{
    System.out.println(cityDataRepository.findById(cityname));
    return cityDataRepository.findById(cityname);
}

}
