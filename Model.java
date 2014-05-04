/**
 * Model for OLAP operations.
 * Constructs a SQL statement from the specified options 
 * the user has selected in the view.
 * 
 * CS 157B - Spring 2014
 * @author Kevin Tan
 */
public class Model 
{
	public static String SQL = ""; // The SQL statement to be executed.
	
	/**
	 * Constructs an SQL statement.
	 * @param dimensions and attributes of the schema.
	 * @return SQL the SQL statement that will be executed.
	 */
	public static String constructSQL(
				boolean sales_fact,
					boolean dollar_sales,
					boolean unit_sales,
					boolean dollar_cost,
					boolean customer_count,
				boolean product,
					boolean description,
					boolean full_description,
					boolean SKU_number,
					boolean package_size,
					boolean brand,
					boolean subcategory,
					boolean category,
					boolean department,
					boolean package_type,
					boolean diet_type,
					boolean weight,
					boolean weight_unit_of_measure,
					boolean units_per_retail_case,
					boolean units_per_shipping_case,
					boolean cases_per_pallet,
					boolean shelf_width_cm,
					boolean shelf_height_cm,
					boolean shelf_depth_cm,
				boolean promotion,
					boolean promotion_name,
					boolean price_reduction_type,
					boolean ad_type,
					boolean display_type,
					boolean coupon_type,
					boolean ad_media_type,
					boolean display_provider,
					boolean promo_cost,
					boolean promo_begin_date,
					boolean promo_end_date,
				boolean store,
					boolean name,
					boolean store_number,
					boolean store_street_address,
					boolean city,
					boolean store_county,
					boolean store_state,
					boolean store_zip,
					boolean sales_district,
					boolean sales_region,
					boolean store_manager,
					boolean store_phone,
					boolean store_FAX,
					boolean floor_plan_type,
					boolean photo_processing_type,
					boolean finance_services_type,
					boolean first_opened_date,
					boolean last_remodel_date,
					boolean store_sqft,
					boolean grocery_sqft,
					boolean frozen_sqft,
					boolean meat_sqft,
				boolean time,
					boolean date,
					boolean day_of_week,
					boolean day_number_in_month,
					boolean day_number_overall,
					boolean week_number_in_year,
					boolean week_number_overall,
					boolean month,
					boolean quarter,
					boolean fiscal_period,
					boolean year,
					boolean holiday_flag
			)
	{
		SQL += "SELECT";
			// sales_fact table
			if (dollar_sales) SQL += " sum(dollar_sales) as \"dollar sales\",";
			if (unit_sales) SQL += " sum(unit_sales) as \"unit sales\",";
			if (dollar_cost) SQL += " sum(dollar_cost) as \"dollar cost\",";
			if (customer_count) SQL += " sum(customer_count) as \"customer count\","; // sum?		
			// product table
			if (description) SQL += " description,";
			if (full_description) SQL += " full_description,";
			if (SKU_number) SQL += " SKU_number,";
			if (package_size) SQL += " package_size,";
			if (brand) SQL += " brand,";
			if (subcategory) SQL += " subcategory,";
			if (category) SQL += " category,";
			if (department) SQL += " department,";
			if (package_type) SQL += " package_type,";
			if (diet_type) SQL += " diet_type,";
			if (weight) SQL += " weight,";
			if (weight_unit_of_measure) SQL += " weight_unit_of_measure,";
			if (units_per_retail_case) SQL += " units_per_retail_case,";
			if (units_per_shipping_case) SQL += " units_per_shipping_case,";
			if (cases_per_pallet) SQL += " cases_per_pallet,";
			if (shelf_width_cm) SQL += " shelf_width_cm,";
			if (shelf_height_cm) SQL += " shelf_height_cm,";
			if (shelf_depth_cm) SQL += " shelf_depth_cm,";
			// promotion table
			if (promotion_name) SQL += " promotion_name,";
			if (price_reduction_type) SQL += " price_reduction_type,";
			if (ad_type) SQL += " ad_type,";
			if (display_type) SQL += " display_type,";
			if (coupon_type) SQL += " coupon_type,";
			if (ad_media_type) SQL += " ad_media_type,";
			if (display_provider) SQL += " display_provider,";
			if (promo_cost) SQL += " promo_cost,";
			if (promo_begin_date) SQL += " promo_begin_date,";
			if (promo_end_date) SQL += " promo_end_date,";
			// store table
			if (name) SQL += " name,";
			if (store_number) SQL += " store_number,";
			if (store_street_address) SQL += " store_street_address,";
			if (city) SQL += " city,";
			if (store_county) SQL += " store_county,";
			if (store_state) SQL += " store_state,";
			if (store_zip) SQL += " store_zip,";
			if (sales_district) SQL += " sales_district,";
			if (sales_region) SQL += " sales_region,";
			if (store_manager) SQL += " store_manager,";
			if (store_phone) SQL += " store_phone,";
			if (store_FAX) SQL += " store_FAX,";
			if (floor_plan_type) SQL += " floor_plan_type,";
			if (photo_processing_type) SQL += " photo_processing_type,";
			if (finance_services_type) SQL += " finance_services_type,";
			if (first_opened_date) SQL += " first_opened_date,";
			if (last_remodel_date) SQL += " last_remodel_date,";
			if (store_sqft) SQL += " store_sqft,";
			if (grocery_sqft) SQL += " grocery_sqft,";
			if (frozen_sqft) SQL += " frozen_sqft,";
			if (meat_sqft) SQL += " meat_sqft,";
			// time table
			if (date) SQL += " date,";
			if (day_of_week) SQL += " day_of_week,";
			if (day_number_in_month) SQL += " day_number_in_month,";
			if (day_number_overall) SQL += " day_number_overall,";
			if (week_number_in_year) SQL += " week_number_in_year,";
			if (week_number_overall) SQL += " week_number_overall,";
			if (month) SQL += " month,";
			if (quarter) SQL += " quarter,";
			if (fiscal_period) SQL += " fiscal_period,";
			if (year) SQL += " year,";
			if (holiday_flag) SQL += " holiday_flag,";
			SQL = removeLastChar(SQL, 1); // Remove the trailing comma.
		
		SQL += " FROM";
			if (sales_fact) SQL += " sales_fact,";
			if (product) SQL += " product,";
			if (promotion) SQL += " promotion,";
			if (store) SQL += " store,";
			if (time) SQL += " time,";
			SQL = removeLastChar(SQL, 1); // Remove the trailing comma.
		
		SQL += " WHERE";
			if (product) SQL += " sales_fact.product_key = product.product_key AND";
			if (promotion) SQL += " sales_fact.promotion_key = promotion.promotion_key AND";
			if (store) SQL += " sales_fact.store_key = store.store_key AND";
			if (time) SQL += " sales_fact.time_key = time.time_key AND";
			SQL = removeLastChar(SQL, 4); // Remove the trailing AND.
			
		SQL += " GROUP BY";
			// product table
			if (description) SQL += " description,";
			if (full_description) SQL += " full_description,";
			if (SKU_number) SQL += " SKU_number,";
			if (package_size) SQL += " package_size,";
			if (brand) SQL += " brand,";
			if (subcategory) SQL += " subcategory,";
			if (category) SQL += " category,";
			if (department) SQL += " department,";
			if (package_type) SQL += " package_type,";
			if (diet_type) SQL += " diet_type,";
			if (weight) SQL += " weight,";
			if (weight_unit_of_measure) SQL += " weight_unit_of_measure,";
			if (units_per_retail_case) SQL += " units_per_retail_case,";
			if (units_per_shipping_case) SQL += " units_per_shipping_case,";
			if (cases_per_pallet) SQL += " cases_per_pallet,";
			if (shelf_width_cm) SQL += " shelf_width_cm,";
			if (shelf_height_cm) SQL += " shelf_height_cm,";
			if (shelf_depth_cm) SQL += " shelf_depth_cm,";
			// promotion table
			if (promotion_name) SQL += " promotion_name,";
			if (price_reduction_type) SQL += " price_reduction_type,";
			if (ad_type) SQL += " ad_type,";
			if (display_type) SQL += " display_type,";
			if (coupon_type) SQL += " coupon_type,";
			if (ad_media_type) SQL += " ad_media_type,";
			if (display_provider) SQL += " display_provider,";
			if (promo_cost) SQL += " promo_cost,";
			if (promo_begin_date) SQL += " promo_begin_date,";
			if (promo_end_date) SQL += " promo_end_date,";
			// store table
			if (name) SQL += " name,";
			if (store_number) SQL += " store_number,";
			if (store_street_address) SQL += " store_street_address,";
			if (city) SQL += " city,";
			if (store_county) SQL += " store_county,";
			if (store_state) SQL += " store_state,";
			if (store_zip) SQL += " store_zip,";
			if (sales_district) SQL += " sales_district,";
			if (sales_region) SQL += " sales_region,";
			if (store_manager) SQL += " store_manager,";
			if (store_phone) SQL += " store_phone,";
			if (store_FAX) SQL += " store_FAX,";
			if (floor_plan_type) SQL += " floor_plan_type,";
			if (photo_processing_type) SQL += " photo_processing_type,";
			if (finance_services_type) SQL += " finance_services_type,";
			if (first_opened_date) SQL += " first_opened_date,";
			if (last_remodel_date) SQL += " last_remodel_date,";
			if (store_sqft) SQL += " store_sqft,";
			if (grocery_sqft) SQL += " grocery_sqft,";
			if (frozen_sqft) SQL += " frozen_sqft,";
			if (meat_sqft) SQL += " meat_sqft,";
			// time table
			if (date) SQL += " date,";
			if (day_of_week) SQL += " day_of_week,";
			if (day_number_in_month) SQL += " day_number_in_month,";
			if (day_number_overall) SQL += " day_number_overall,";
			if (week_number_in_year) SQL += " week_number_in_year,";
			if (week_number_overall) SQL += " week_number_overall,";
			if (month) SQL += " month,";
			if (quarter) SQL += " quarter,";
			if (fiscal_period) SQL += " fiscal_period,";
			if (year) SQL += " year,";
			if (holiday_flag) SQL += " holiday_flag,";
			SQL = removeLastChar(SQL, 1); // Remove the trailing comma.
			
		return SQL;
	}
	
	/**
	 * Removes the last character(s) of a string.
	 * @param s the string where the last character will be removed.
	 * @param i the amount of characters to remove.
	 * @return string with the last character removed.
	 */
	private static String removeLastChar(String s, int i) 
	{
        return s.substring(0, s.length() - i);
    }
	
	/**
	 * For testing purposes.
	 * @param args not used.
	 */
	public static void main(String[] args) 
	{	System.out.println(
			constructSQL(
					true,//boolean sales_fact,
						false,//boolean dollar_sales,
						true,//boolean unit_sales,
						false,//boolean dollar_cost,
						false,//boolean customer_count,
					true,//boolean product,
						false,//boolean description,
						false,//boolean full_description,
						false,//boolean SKU_number,
						false,//boolean package_size,
						true,//boolean brand,
						false,//boolean subcategory,
						false,//boolean category,
						false,//boolean department,
						false,//boolean package_type,
						false,//boolean diet_type,
						true,//boolean weight,
						false,//boolean weight_unit_of_measure,
						false,//boolean units_per_retail_case,
						false,//boolean units_per_shipping_case,
						false,//boolean cases_per_pallet,
						false,//boolean shelf_width_cm,
						false,//boolean shelf_height_cm,
						false,//boolean shelf_depth_cm,
						false,//boolean promotion,
						false,//boolean promotion_name,
						false,//boolean price_reduction_type,
						false,//boolean ad_type,
						false,//boolean display_type,
						false,//boolean coupon_type,
						false,//boolean ad_media_type,
						false,//boolean display_provider,
						false,//boolean promo_cost,
						false,//boolean promo_begin_date,
						false,//boolean promo_end_date,
					false,//boolean store,
						false,//boolean name,
						false,//boolean store_number,
						false,//boolean store_street_address,
						false,//boolean city,
						false,//boolean store_county,
						false,//boolean store_state,
						false,//boolean store_zip,
						false,//boolean sales_district,
						false,//boolean sales_region,
						false,//boolean store_manager,
						false,//boolean store_phone,
						false,//boolean store_FAX,
						false,//boolean floor_plan_type,
						false,//boolean photo_processing_type,
						false,//boolean finance_services_type,
						false,//boolean first_opened_date,
						false,//boolean last_remodel_date,
						false,//boolean store_sqft,
						false,//boolean grocery_sqft,
						false,//boolean frozen_sqft,
						false,//boolean meat_sqft,
					false,//boolean time,
						false,//boolean date,
						false,//boolean day_of_week,
						false,//boolean day_number_in_month,
						false,//boolean day_number_overall,
						false,//boolean week_number_in_year,
						false,//boolean week_number_overall,
						false,//boolean month,
						false,//boolean quarter,
						false,//boolean fiscal_period,
						false,//boolean year,
						false//boolean holiday_flag
				) // end constructSQL method.
		); // end System.out.println.

	} // end main method.

}
