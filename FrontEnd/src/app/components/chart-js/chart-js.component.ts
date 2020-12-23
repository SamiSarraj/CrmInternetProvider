import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import {Observable, of} from 'rxjs';
import {APIService} from '../../services/api.service';
import {CountNew} from './count-new';
import {CountPackages} from './count-packages';

@Component({
  selector: 'app-chart-js',
  templateUrl: './chart-js.component.html',
  styleUrls: ['./chart-js.component.css']
})
export class ChartJsComponent implements OnInit {

chartCountNetPlansByPackage = [];
chartCountCustomersAndEmployees = [];
chartTicketsByState = [];
chartEmployeesByRole = [];
chartHelpDiskByMonth = [];
chartTicketsByMonth = [];
/*countNewCustomers = new CountNew();
countNewEmployees = new CountNew();
countPackages = new CountPackages();*/


  constructor(private apiService: APIService) { }

  ngOnInit() {
    /*this.apiService.getAllCustomersChart().subscribe(data => this.countNewCustomers = data,
      err => console.log(err));*/
    this.apiService.getAllHelpDiskByMonth().subscribe(data => {
      /*console.log(data);*/
      /*console.log(this.countNewCustomers);*/
      this.chartHelpDiskByMonth = new Chart('countHelpDiskByMonth', {
          type: 'bar',
          data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July',
              'August', 'September', 'October', 'November', 'December'],
            datasets: [
              {
                label: 'Help Disk',
                borderColor: 'rgba(0, 150, 136, 0.76)',
                borderWidth: '1',
                backgroundColor: 'red',
                data: data.amount
              },
            ]
          },
          options: {
            scales: {
              yAxes: [{
                ticks: {
                  min: 0,
                  stepSize: 5,
                  beginAtZero: true
                }
              }]
            }
          }
        }
      );
    });
    this.apiService.getAllTicketsByMonth().subscribe(data => {
      this.chartTicketsByMonth = new Chart('countTicketsByMonth', {
        type: 'line',
        data: {
          labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July',
            'August', 'September', 'October', 'November', 'December'],
          datasets: [
            {
              label: 'Tickets',
              borderColor: '#009688',
              borderWidth: '1',
              backgroundColor: '#009688',
              pointHighlightStroke: '#1ab394',
              data: data.amount
            },
          ]
        },
        options: {
          responsive: true,
          tooltips: {
            mode: 'index',
            intersect: false
          },
          hover: {
            mode: 'nearest',
            intersect: true
          }

        }
      });
    });
    this.apiService.getAllTicketsByState().subscribe(data => {
      this.chartTicketsByState = new Chart('countTicketsByState', {
        type: 'polarArea',
        data: {
          datasets: [{
            data: data.amount,
            backgroundColor: [
              '#008000',
              '#CC0000',
              '#CCCC00',
              '#0000B2'
            ],
          }],
          labels: [
            'Completed',
            'Failed',
            'Need Attention',
            'Resolving'
          ]
        },
        options: {
          responsive: true
        }
      });
    });
    this.apiService.getAllEmployeesByRole().subscribe(data => {
      this.chartEmployeesByRole = new Chart('countEmployeesByRole', {
        type: 'doughnut',
        data: {
          datasets: [{
            data: data.amount,
            backgroundColor: [
              '#008000',
              '#CC0000',
              '#CCCC00',
              '#0000B2',
              '#000000'
            ],
          }],
          labels: [
            'Internet service',
            'Internet Packages',
            'Payment',
            'Crm Website',
            'Customer Service'
          ]
        },
        options: {
          responsive: true
        }
      });
    });
    this.apiService.getAllCustomersChart().subscribe(data => {
      this.apiService.getAllEmployeesChart().subscribe(data2 => {
        /*console.log(data);*/
        /*console.log(this.countNewCustomers);*/
        this.chartCountCustomersAndEmployees = new Chart('countCustomersAndEmployeesByMonth', {
            type: 'bar',
            data: {
              labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July',
                'August', 'September', 'October', 'November', 'December'],
              datasets: [
                {
                  label: 'Customers',
                  borderColor: '#009688',
                  borderWidth: '1',
                  backgroundColor: '#CC0000',
                  data: data.amount
                },
                {
                  label: 'Employees',
                  borderColor: '#009688',
                  borderWidth: '1',
                  backgroundColor: '#0000B2',
                  data: data2.amount
                }
              ]
            },
            options: {
              scales: {
                yAxes: [{
                  ticks: {
                    min: 0,
                    stepSize: 5,
                    beginAtZero: true
                  }
                }]
              }
            }
          }
        );
      });
    });
    this.apiService.getAllPackagesPieChart().subscribe(data => {
      this.chartCountNetPlansByPackage = new Chart('countNetPlansByPackages', {
        type: 'pie',
        data: {
          datasets: [{
            data: data.amount,
            backgroundColor: [
              '#009688'
            ],
            hoverBackgroundColor: [
              '#009688'
            ]

          }],
          labels: data.title
        },
        options: {
          responsive: true
        }
      });
    });
    /*this.getAllEmployees();*/
}

/*getAllCustomers() {
  this.apiService.getAllCustomersChart().subscribe(data => this.countNewCustomers = data
    ,
    err => console.log(err));
}
  getAllEmployees() {
    this.apiService.getAllEmployeesChart().subscribe(data => this.countNewEmployees = data
      ,
      err => console.log(err));
  }
  getAllPackages() {
    this.apiService.getAllPackagesPieChart().subscribe(data => this.countPackages = data
      ,
      err => console.log(err));
  }*/
}
