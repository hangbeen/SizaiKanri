<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>エラー発生</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            text-align: center;
            margin-top: 50px;
        }
        .error-container {
            background: #fff;
            border: 1px solid #ddd;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
            display: inline-block;
            padding: 20px 40px;
            border-radius: 8px;
        }
        h1 {
            color: #e74c3c;
        }
        p {
            font-size: 16px;
        }
        .button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: #fff;
            background-color: #3498db;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #2980b9;
        }
        .error-code {
            color: #777;
            margin-top: 10px;
            font-size: 12px;
        }
    </style>
    <script>
        function goBack() {
            history.back(); //  以前ページに戻る　// 이전 페이지로 돌아가기 
        }
    </script>
</head>
<body>
    <div class="error-container">
        <h1>エラーが発生しました！</h1>
        <p>ご迷惑をおかけして 申し訳ございません。</p>
        <p>もう少し待ってた後にやり直してください。</p>

        <div class="error-code">
            <p>エラーコード: <%= request.getAttribute("errorCode") != null ? request.getAttribute("errorCode") : "分からない" %></p>
        </div>

        <div>
            <a href="/" class="button">ホームに戻る</a>
            <button type="button" class="button" onclick="goBack()">以前ページに戻る</button>
        </div>
    </div>
</body>
</html>
