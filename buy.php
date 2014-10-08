<!--
  Student Name: PADILAM RAJKUMAR	1001041479
  URL: http://omega.uta.edu/~rxp1479/project4/buy.php
-->  

<?php
	session_start();	
	if (!isset($_SESSION['items'])) 
	{
	 $_SESSION['items'] = array();
	}
	if (!isset($_SESSION['search'])) 
	{
	 $_SESSION['search'] = array();
	}
	$total=0;
?>

<html>
<head><title>Buy Products</title></head>
<body bgcolor="#E6E6FA";>
<h3 align="center"> ONLINE SHOPPING CART </h3>
<br><br>

<?php
	echo "<fieldset><legend><h3 >Shopping cart</h3></legend><br><br>";
	
	if(isset($_GET["buy"]))
	{		
		$ProductId=$_GET['buy'];
		$ImageSrc=$_SESSION['search'][$ProductId]['ImageSrc'];
		$Name=$_SESSION['search'][$ProductId]['Name'];
		
		$URL=$_SESSION['search'][$ProductId]['URL'];
		$Price=$_SESSION['search'][$ProductId]['Price'];
		
		if(isset($_SESSION['items']))
		{
		$_SESSION['items'][$ProductId] = array('ID' => $ProductId,'ImageSrc' => $ImageSrc, 'Name' => $Name, "URL" =>$URL, "Price" =>$Price);
		}
		
	}
	
	if(isset($_GET['delete']))
	{
		unset($_SESSION['items'][$_GET['delete']]);
	}
	
	if(isset($_GET['clear']))
	{
		if(isset($_SESSION['items']))
		unset($_SESSION['items']);
			
		if (!isset($_SESSION['items'])) 
		{
		 $_SESSION['items'] = array();
		}
	}
	
		global $total;
		$total=0;
			if(isset( $_SESSION['items']) && (count($_SESSION['items'])>0))
			{
				  echo "<table border=1>";
				  echo "<th>Product Image</th>";
				  echo "<th>Product Name</th>";
				  echo "<th>Price</th>";
				 
				 foreach($_SESSION['items'] as $item)
				 {
					echo "<tr>";
					echo "<td><a href=".$item['URL']."><img src=".$item['ImageSrc']."height='75px' width='75px'></img></a></td>";
					echo "<td>".$item['Name']."</td>";
					echo "<td>".'$'.$item['Price']."</td>";
					echo "<td><a href='buy.php?delete=".$item['ID']."'>delete</a></td>";
					$total=$total+$item['Price'];
					echo "</tr>";
				 }
				 echo "</table>";
				echo "<br>";
				echo "Total amount :$".$total;			
			}else{
				echo "Your Shopping Cart is Presently Empty";			
			}
			echo "</fieldset><br>";
	
	if(isset( $_SESSION['items']) && (count($_SESSION['items'])>0))
	{
	echo "<form action='buy.php' method='GET'>";
	echo "<input type='hidden' name='clear' value='1'/>";
    echo "<input type='submit' value='Empty Shopping Basket'/>";
    echo "</form>";
	}	
	
	echo "<br><br><br>";
	
	
	if(isset($_GET["search"]))
	{
	$string = $_GET["search"];
	$string = str_replace(' ', '+', $string); 
	error_reporting(E_ALL);
	ini_set('display_errors','On');
	$xmlstr = file_get_contents('http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&trackingId=7000610&keyword='.$string);
	$xml = new SimpleXMLElement($xmlstr);
	header('Content-Type: text');

	
	  echo "<table border=1>";
	  echo "<th>Product Image</th>";
	  echo "<th>Product Name</th>";
	  echo "<th>Price</th>";
	  if(isset($_SESSION['search']))
	  {
		  unset($_SESSION['search']);
		  $_SESSION['search'] = array();
	   }
	foreach($xml->categories->category->items->product as $products)
		{
		$imageURL = (string) $products->images->image[0]->sourceURL;
		$id = (string) $products['id'];
		$Name=(string)$products->name;
		$Price=(string) $products->minPrice;		
		$URL=(string)$products->productOffersURL;
		
		
		echo "<tr>";
		echo "<td><a href= 'buy.php?buy=".$id."'><img src=".$imageURL."></img></a></td>";
		echo "<td>".$products->name."</td>";
		echo "<td>".'$'.$products->minPrice."</td></tr>";
			if(isset($_SESSION['search']))
			{
				$_SESSION['search'][$id] = array('ID' => $id,'ImageSrc' => $imageURL, 'Name' => $Name, "URL" =>$URL, "Price" =>$Price);
			}
		}
	 }
	
			
?>

<div id="form">	
	<form action="buy.php" method="GET">
	<fieldset><legend><h3>Find Products </h3></legend>
       <label>Search Terms: <input type="text" name="search"/></label>
       <input type="submit" value="Find"/>
	</fieldset>   
    </form>
	<!--<label id="testing"> testing </label>-->
	<br><br>
</div>		
 </body>
</html>
