<#macro csrf>
    <input type="hidden" id="csrfToken" name="${csrfToken.parameterName}" value="${csrfToken.token}">
</#macro>