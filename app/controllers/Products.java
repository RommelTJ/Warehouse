package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.list;
import views.html.products.details;
import java.util.List;
import models.Product;
import play.data.Form;

public class Products extends Controller {

    private static final Form<Product> productForm = Form.form(Product.class);

    public static Result index() {
        return redirect(routes.Products.list(1));
    }

    public static Result list(int page) {
        List<Product> products = Product.findAll();
        return ok(list.render(products));
    }

    public static Result newProduct(){
        return ok(details.render(productForm));
    }

    public static Result details(String ean){
        final Product product = Product.findByEan(ean);
        if (product == null){
            return notFound(String.format("Product %s does not exist.", ean));
        }
        Form<Product> filledForm = productForm.fill(product);
        return ok(details.render(filledForm));
    }

    public static Result save(){
        Form <Product> boundForm = productForm.bindFromRequest();
        if (boundForm.hasErrors()){
            flash("Error!", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Product product = boundForm.get();
        product.save();
        flash("Success!", String.format("Succesfully added product %s", product));
        return redirect(routes.Products.list(1));
    }

    public static Result delete(String ean){
        final Product product = Product.findByEan(ean);
        if (product==null){
            return notFound(String.format("Product %s does not exist.", ean));
        }
        Product.remove(product);
        return redirect(routes.Products.list(1));
    }
}