<%--
  Created by IntelliJ IDEA.
  User: MrBhite
  Date: 2022/11/4
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<h3>Account Information</h3>
<table>
  <tr>
    <td>First name:</td>
    <td><input type="text" name="account.firstName" value="${sessionScope.account.firstName}"></td>
  </tr>
  <tr>
    <td>Last name:</td>
    <td><input type="text" name="account.lastName" value="${sessionScope.account.lastName}"></td>
  </tr>
  <tr>
    <td>Email:</td>
    <td><input type="text" name="account.email" value="${sessionScope.account.email}"></td>
  </tr>
  <tr>
    <td>Phone:</td>
    <td><input type="text" name="account.phone" value="${sessionScope.account.phone}"></td>
  </tr>
  <tr>
    <td>Address 1:</td>
    <td><input type="text" name="account.address1" value="${sessionScope.account.address1}"></td>
  </tr>
  <tr>
    <td>Address 2:</td>
    <td><input type="text" name="account.address2" value="${sessionScope.account.address2}"></td>
  </tr>
  <tr>
    <td>City:</td>
    <td><input type="text" name="account.city" value="${sessionScope.account.city}"></td>
  </tr>
  <tr>
    <td>State:</td>
    <td><input type="text" name="account.state" value="${sessionScope.account.state}"></td>
  </tr>
  <tr>
    <td>Zip:</td>
    <td><input type="text" name="account.zip" value="${sessionScope.account.zip}"></td>
  </tr>
  <tr>
    <td>Country:</td>
    <td><input type="text" name="account.country" value="${sessionScope.account.country}"></td>
  </tr>
</table>

<h3>Profile Information</h3>

<table>
  <tr>
    <td>Language Preference:</td>
    <td>
      <select id="account.languagePreference" name="account.languagePreference">
        <option value="english" ${sessionScope.account.languagePreference=='english'?'selected':''}>
          english
        </option>
        <option value="japanese" ${sessionScope.account.languagePreference=='japanese'?'selected':''}>
          japanese
        </option>
      </select>
    </td>
  </tr>
  <tr>
    <td>Favourite Category:</td>
    <td>
      <select id="account.favouriteCategoryId" name="account.favouriteCategoryId">
        <option value="FISH" ${sessionScope.account.favouriteCategoryId=='FISH'?'selected':''}>
          FISH
        </option>
        <option value="DOGS" ${sessionScope.account.favouriteCategoryId=='DOGS'?'selected':''}>
          DOGS
        </option>
        <option value="REPTILES" ${sessionScope.account.favouriteCategoryId=='REPTILES'?'selected':''}>
          REPTILES
        </option>
        <option value="CATS" ${sessionScope.account.favouriteCategoryId=='CATS'?'selected':''}>
          CATS
        </option>
        <option value="BIRDS" ${sessionScope.account.favouriteCategoryId=='BIRDS'?'selected':''}>
          BIRDS
        </option>
      </select>
    </td>
  </tr>
  <tr>
    <td>Enable MyList</td>
    <td><input type="checkbox" name="account.listOption" value="true" ${sessionScope.account.listOption?'checked':''}></td>
  </tr>
  <tr>
    <td>Enable MyBanner</td>
    <td><input type="checkbox" name="account.bannerOption" value="true"${sessionScope.account.bannerOption?'checked':''}></td>
  </tr>

</table>