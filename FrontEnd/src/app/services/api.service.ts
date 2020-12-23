import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IdResponse} from '../models/id-response.model';
import {CodeResponse} from '../models/code-response.model';
import {User} from '../models/user.model';
import {Photo} from '../models/photo.model';
import {Customer} from '../models/customer.model';
import {TicketEmployee} from '../models/ticket-employee.model';
import {TicketAsignList} from '../models/ticket-asign-list.model';
import {NetPackagesCreate} from '../models/net-packages-create.model';
import {ApproveNetPlans} from '../models/approve-net-plans.model';
import {NetPlansListUser} from '../models/net-plans-list-user.model';
import {Message} from '../models/message.model';
import {Compose} from '../models/compose.model';
import {CalendarModel} from '../models/calendar.model';
import {HelpDiskList} from '../models/help-disk-list';
import {CountNew} from '../components/chart-js/count-new';
import {CountPackages} from '../components/chart-js/count-packages';
import {TicketCreate} from '../models/ticket-create.model';
import {TicketDetails} from '../models/ticket-details.model';
import {HelpDiskDetails} from '../models/help-disk-details.model';
import {NetPlanCustomers} from '../models/net-plan-customers';
import {UserProfile} from '../models/user-profile.model';
import {DashboardTopWorkers} from '../models/dashboard-top-workers.model';
import {UserList} from '../models/user-list.model';


@Injectable({
  providedIn: 'root'
})

export class APIService {
  API_URL = '/crm';

  constructor(private httpClient: HttpClient) {
  }
  addUser(user) {
    return this.httpClient.post(`${this.API_URL}/user/addUser`, user);
  }
  addCustomer(customer) {
    return this.httpClient.post(`${this.API_URL}/user/addCustomer`, customer);
  }
  getAllCustomers() {
    return this.httpClient.get<Customer[]>(`${this.API_URL}/user/allCustomers`);
  }
  getAllUsers() {
    return this.httpClient.get<User[]>(`${this.API_URL}/user/allUsers`);
  }
  getAllEmployees() {
    return this.httpClient.get<User[]>(`${this.API_URL}/user/allEmployees`);

  }
  addTicket(ticket) {
    return this.httpClient.post(`${this.API_URL}/ticket/addTicket`, ticket);
  }
  asignTicket(ticket) {
    return this.httpClient.post(`${this.API_URL}/processUnit/assignTicket`, ticket);
  }
  getAllEmployeeAsignTicket() {
    return this.httpClient.get<TicketEmployee[]>(`${this.API_URL}/ticket/unassignedTickets/employee`);

  }
  getAllUnassignedTickets() {
    return this.httpClient.get<TicketAsignList[]>(`${this.API_URL}/ticket/unassignedTickets/getAll`);
  }
  addPackage(packageo) {
    return this.httpClient.post(`${this.API_URL}/net-packages/addNewPackage`, packageo);

  }
  getAllPackages() {
    return this.httpClient.get<NetPackagesCreate[]>(`${this.API_URL}/net-packages/getAll`);
  }

  getAllNotCheckedPlans() {
    return this.httpClient.get<ApproveNetPlans[]>(`${this.API_URL}/plan/allNotChecked`);
  }
  getAllNetPlans() {
    return this.httpClient.get<NetPlansListUser[]>(`${this.API_URL}/plan/allPlans`);
  }
  getOnePackageById(id) {
    return this.httpClient.get<NetPackagesCreate>(`${this.API_URL}/net-packages/getByTitle/` + id);
  }
  getCustomerValidPlan() {
    return this.httpClient.get<NetPlansListUser>(`${this.API_URL}/plan/customer-myplan`);
  }
  getCustomerAllPlans() {
    return this.httpClient.get<NetPlansListUser[]>(`${this.API_URL}/plan/customer-myplans`);
  }
  modifyPackage(packageo, idPackage) {
    return this.httpClient.put(`${this.API_URL}/net-packages/modifyPackage/`, packageo + idPackage);

  }
  getAllCheckedMessages() {
    return this.httpClient.get<Message[]>(`${this.API_URL}/message/inbox/checked`);

  }
  getAllNotCheckedMessages() {
    return this.httpClient.get<Message[]>(`${this.API_URL}/message/inbox/notChecked`);

  }
  getUserFullName() {
    return this.httpClient.get<Compose>(`${this.API_URL}/message/compose/senderFullName`);

  }
  createMessage(message) {
    return this.httpClient.post(`${this.API_URL}/message/compose/newMessage`, message);

  }
  getOneMessage(id) {
    return this.httpClient.get<Message>(`${this.API_URL}/message/inbox/` + id);

  }
  getAllSentMessages() {
    return this.httpClient.get<Message[]>(`${this.API_URL}/message/sentInbox`);

  }
  addEvent(event) {
    return this.httpClient.post(`${this.API_URL}/calender`, event);
  }
  getAllEvents() {
    return this.httpClient.get<CalendarModel[]>(`${this.API_URL}/calender`);
  }
  getAllHelpDisk(topic) {
    return this.httpClient.get<HelpDiskList[]>(`${this.API_URL}/help-disk/getAllByTopic/` + topic);

  }
  addHelp(help) {
    return this.httpClient.post(`${this.API_URL}/help-disk/addNewHelp`, help);

  }
  getAllCustomersChart() {
    return this.httpClient.get<CountNew>(`${this.API_URL}/charts/getAllCustomers`);

  }
  getAllEmployeesChart() {
    return this.httpClient.get<CountNew>(`${this.API_URL}/charts/getAllEmployees`);

  }
  getAllPackagesPieChart() {
    return this.httpClient.get<CountPackages>(`${this.API_URL}/charts/getAllPackages`);

  }
  getAllCustomerTickets() {
    return this.httpClient.get<TicketCreate[]>(`${this.API_URL}/ticket/allCustomerTickets`);
  }
  getAllTickets() {
    return this.httpClient.get<TicketCreate[]>(`${this.API_URL}/ticket/allTickets`);
  }
  getAllEmployeeTickets () {
    return this.httpClient.get<TicketCreate[]>(`${this.API_URL}/ticket/allEmployeeTickets`);

  }
  getOneTicket(ticketId) {
    return this.httpClient.get<TicketDetails>(`${this.API_URL}/processUnit/getTicketUnit/` + ticketId);

  }
  addComment(comment) {
    return this.httpClient.post(`${this.API_URL}/processUnit/addComment`, comment);

  }
  addUserInfomration(infromation) {
    return this.httpClient.post(`${this.API_URL}/user/saveInformationUser`, infromation);

  }
  getOneHelpDisk(helpDiskId) {
  return this.httpClient.get<HelpDiskDetails>(`${this.API_URL}/help-disk/getHelpDiskById/` + helpDiskId);

  }
  addCommentHelpDisk(comment) {
    return this.httpClient.post(`${this.API_URL}/help-disk/addComment`, comment);

  }
  getAllCustomersNetPlans() {
    return this.httpClient.get<NetPlanCustomers[]>(`${this.API_URL}/plan/getAllCustomers/`);

  }
  asignNetPlan(netPlan) {
    return this.httpClient.post(`${this.API_URL}/plan/createPlanForCustomer`, netPlan);
  }
  getUserInformation(employeeId) {
    return this.httpClient.get<UserProfile>(`${this.API_URL}/user/profile/` + employeeId);
  }
  getMyUserInformation() {
    return this.httpClient.get<UserProfile>(`${this.API_URL}/user/myProfile`);

  }
  addCommentAndRating(comment) {
    return this.httpClient.post(`${this.API_URL}/user/addRatingAndComments`, comment);

  }
  modifyUser(user) {
    return this.httpClient.put(`${this.API_URL}/user/profileModify`, user);

  }
  getAllHelpDiskByMonth() {
    return this.httpClient.get<CountPackages>(`${this.API_URL}/charts/getAllHelpDiskByMonth`);
  }
  getAllTicketsByMonth() {
    return this.httpClient.get<CountPackages>(`${this.API_URL}/charts/getAllTicketsByMonth`);
  }
  getAllTicketsByState() {
    return this.httpClient.get<CountPackages>(`${this.API_URL}/charts/getAllTicketsByState`);
  }
  getAllEmployeesByRole() {
    return this.httpClient.get<CountPackages>(`${this.API_URL}/charts/getAllEmployeesByRole`);
  }
  getAllDashboardUserNumbers() {
    return this.httpClient.get<CountPackages>(`${this.API_URL}/home/getAllDashboardUserNumbers`);
  }
  getAllTicketsNumbers() {
    return this.httpClient.get<CountPackages>(`${this.API_URL}/home/getAllTicketsNumbers`);
  }
  getTopWorkers() {
    return this.httpClient.get<DashboardTopWorkers[]>(`${this.API_URL}/home/getTopWorkers`);
  }
  updateUser(user) {
    return this.httpClient.put(`${this.API_URL}/user/putUser`, user);
  }
  getAllEmployeesWithTickets() {
    return this.httpClient.get<UserList[]>(`${this.API_URL}/ticket/getAllTicketsNumbersByEmployee`);

  }
}

