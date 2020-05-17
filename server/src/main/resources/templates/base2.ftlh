<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring />
<#macro content></#macro>
<#macro main>
    <!doctype html>
    <html lang="en">

    <body>
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="/main">
            <img src="https://www.meme-arsenal.com/memes/790c8ef330ea19d59921b9b87ea911ca.jpg" width="150" height="75"
                 class="d-inline-block align-top" alt="">
        </a>
        <div class="navbar-button-wrapper">

            <a href="/main" class="btn btn-light">MAIN</a>

        </div>
    </nav>
    <@content/>
    </body>
    </html>
</#macro>