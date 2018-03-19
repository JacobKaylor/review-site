//const xhr = new XMLHttpRequest()
//xhr.onreadystatechange = function() {
//	if (xhr.readyState === 4 && xhr.status === 200) {
//		const res = JSON.parse(xhr.response);
//
//		if(res.length) {
//			res.forEach(function(review) {
//				appendOneElementToBody(review)
//			})
//		} else {
//			appendOneElementToBody(res)
//		}
//		
//		function appendOneElementToBody(res) {
//			const body = document.body
//
//			const reviewContainer = document.createElement('div')
//			reviewContainer.classList.add('reviewContainer')
//
//			appendElement(reviewContainer, createElement('h2', res.name))
//			appendElement(reviewContainer, createElement('p', res.description))
//			appendElement(reviewContainer, createElement('h3', res.category.name))
//			res.category.reviewsUrls.forEach(function(reviewsUrl) {
//				const courseLink = createElement('a', reviewsUrl)
//				reviewLink.setAttribute('href', reviewsUrl)
//				appendElement(reviewContainer, reviewLink)
//
//			})
//
//			appendElement(body, reviewContainer)
//		}
//
//		function createElement(elem, textValue) {
//			const newElem = document.createElement(elem)
//			newElem.innerText = textValue
//			return newElem
//		}
//
//		function appendElement(parent, child) {
//			parent.appendChild(child)
//		}
//
//		function showAllPropsInObject(object) {
//			for (prop in res) {
//				console.log(`${prop}: ${res[prop]}`)
//				// prop + ': ' + res[prop]
//			}
//		}
//
//		console.log(res)
//	}
//}
//
//xhr.open('GET', 'http://localhost:8080/reviews', true)
//xhr.send()