<!-- all js here -->
<script src="/homepage_frontend/assets\js\vendor\jquery-1.12.0.min.js"></script>
<script src="/homepage_frontend/assets\js\popper.js"></script>
<script src="/homepage_frontend/assets\js\bootstrap.min.js"></script>
<script src="/homepage_frontend/assets\js\ajax-mail.js"></script>
<script src="/homepage_frontend/assets\js\plugins.js"></script>
<script src="/homepage_frontend/assets\js\main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/11.7.11/sweetalert2.all.min.js"
        integrity="sha512-sZf2OK8od53udtGBBdzSqg3BGjjL3BpM5K4dQgB0mmhumO07aWOLmBK917w5cbdLWPfILjJzKgJcYm+neEBUDw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    function handleChange(page,limit,kw,sortField,order,idCategory,scale){
        let url = "/product?page="+page+"&limit="+limit+"&kw="+kw+"&sortField="+sortField+"&order="+order+"&idCategory="+idCategory+"&scale="+scale;
        window.location.assign(url);
    }
</script>