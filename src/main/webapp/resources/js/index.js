/**
 * 
 */
//const date = new Date(); // 오늘 날짜
let date = new Date(); // 오늘 날짜

const renderCalendar = () => {
	
	const viewYear = date.getFullYear(); // 4자리수의 년도를 얻음
	const viewMonth = date.getMonth(); // 월을 얻음. 왜 1을 더하지?
	
	document.querySelector('.year-month').textContent = viewYear + '년 ' + (viewMonth + 1) + '월'; // 문서의 year-month라고 되어있는 클래스에 해당 문자열을 쏴줌
	
	const prevLast = new Date(viewYear, viewMonth, 0);
	const thisLast = new Date(viewYear, viewMonth + 1, 0);
	
	const PLDate = prevLast.getDate();
	const PLDay = prevLast.getDay();
	
	const TLDate = thisLast.getDate();
	const TLDay = thisLast.getDay();
	
	const prevDates = [];
	const thisDates = [...Array(TLDate + 1).keys()].slice(1);
	const nextDates = [];
	
	if(PLDay !== 6){
		for(let i = 0; i <PLDay + 1; i++){
			prevDates.unshift(PLDate-i);
		}
	}
	
	for(let i = 1; i < 7 - TLDay; i++){
		nextDates.push(i);
	}
	
	const dates = prevDates.concat(thisDates, nextDates);
	const firstDateIndex = dates.indexOf(1);
	const lastDateIndex = dates.lastIndexOf(TLDate);
	
	dates.forEach((date, i) => {
		const condition = i >= firstDateIndex && i < lastDateIndex + 1 ? 'this' : 'other';
		dates[i] = '<div class="date"><span class=' + condition + '>' + date + '</span></div>'
	});
	
	
	document.querySelector('.dates').innerHTML = dates.join('');
	
	const today = new Date();
	if(viewMonth === today.getMonth() && viewYear === today.getFullYear()){
		for(let date of document.querySelectorAll('.this')){
			if(+date.innerHTML === today.getDate()){
				date.classList.add('today');
				break;
			}
		}
	}
	
}

renderCalendar();

const prevMonth = () => {
	date.setMonth(date.getMonth() - 1);
	renderCalendar();
}

const nextMonth = () => {
	date.setMonth(date.getMonth() + 1);
	renderCalendar();
}

const goToday = () => {
	date = new Date();
	renderCalendar();
}




