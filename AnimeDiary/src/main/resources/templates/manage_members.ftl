<#include "base.ftl">

<#macro title>
    Manage Members
</#macro>

<#macro style>
    <link rel="stylesheet" href="<#if theme == 'dark'>/css/dark_theme/dark_manage_members.css<#else>/css/light_theme/manage_members.css</#if>">
</#macro>

<#macro content>
    <div class="card background-card-custom">
        <div class="card-body">
            <table>
                <thead>
                <tr>
                    <th class="td-style">Никнейм</th>
                    <th class="td-style">Email</th>
                    <th class="td-style">Статус</th>
                    <th class="td-style">Действие</th>
                    <th class="td-style">Роль</th>
                </tr>
                </thead>
                <tbody>
                <#list members as member>
                    <tr>
                        <td class="td-style">${member.getNickname()}</td>
                        <td class="td-style">${member.getEmail()}</td>
                        <td id="status_text_${member.getId()}" class="<#if member.getIsNonLocked()>active-text<#else>blocked-text</#if> td-style"><#if member.getIsNonLocked()>Активен<#else>Заблокирован</#if></td>
                        <td class="td-style">
                            <button id="block_btn_${member.getId()}" class="btn <#if member.getIsNonLocked()>btn-primary-custom<#else>btn-secondary-custom</#if>"
                                    onclick="changeMemberBlockStatus(${member.getId()})">
                                <#if member.getIsNonLocked()>Заблокировать<#else>Разблокировать</#if>
                            </button>
                        </td>
                        <#if member.getAuthorities()?size == 2> <#-- это значит что мембер - админ-->
                            <td class="td-style">
                                <button id="admin_role_btn_${member.getId()}" class="btn btn-primary-custom"
                                        onclick="changeMemberRole(${member.getId()}, 'ROLE_ADMIN', false)">
                                    Снять с должности
                                </button>
                            </td>
                        <#else>
                            <td class="td-style">
                                <button id="admin_role_btn_${member.getId()}" class="btn btn-secondary-custom"
                                        onclick="changeMemberRole(${member.getId()}, 'ROLE_ADMIN', true)">
                                    Назначить администратором
                                </button>
                            </td>
                        </#if>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</#macro>

<#macro scripts>
    <script type="application/javascript" src="/js/changeMemberRole.js"></script>
    <script type="application/javascript" src="/js/changeMemberBlockStatus.js"></script>
</#macro>

<@page/>